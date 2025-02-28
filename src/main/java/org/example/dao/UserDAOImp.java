package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.model.UsersModel;

import java.util.List;

public class UserDAOImp implements UserDAO{

    private static final String PERSISTENCE_UNIT_NAME = "hostelmanagement";
    private static EntityManagerFactory factory;
    private EntityManager entityManager;

    public UserDAOImp() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();
    }

    @Override
    public void addUser(UsersModel user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("studentAdded success");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(UsersModel user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long userId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            UsersModel user = entityManager.find(UsersModel.class, userId);
            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public UsersModel getUserById(Long userId) {
        return entityManager.find(UsersModel.class, userId);
    }

    @Override
    public List<UsersModel> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", UsersModel.class).getResultList();
    }
}
