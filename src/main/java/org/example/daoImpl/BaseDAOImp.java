package org.example.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.dao.BaseDAO;

import java.io.Serializable;
import java.security.PrivilegedAction;
import java.util.List;

public abstract class BaseDAOImp<T,ID extends Serializable> implements BaseDAO<T, ID> {

    private final Class<T> entityClass;
    private static final String Persistence_Unit_Name = "hostelmanagement";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(Persistence_Unit_Name);
    private EntityManager entityManager = factory.createEntityManager();

    public BaseDAOImp(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    @Override
    public boolean add(T entity){
        boolean status = false;
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            status = true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return status;
    }
    @Override
    public boolean update(T entity){
        boolean status = false;
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            status = true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return status;
    }
    @Override
    public boolean delete(ID id){
        EntityTransaction transaction = entityManager.getTransaction();
        boolean status = false;
        try{
            transaction.begin();
            T entity = entityManager.find(entityClass , id);
            if (entity != null){
                entityManager.remove(entity);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return status;
    }
    @Override
    public T getById(ID id){
        return entityManager.find(entityClass ,id);
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT e FROM "+ entityClass.getSimpleName()+" e "+entityClass).getResultList();
    }
}
