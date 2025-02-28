package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.UserDAO;
import org.example.model.UsersModel;
import org.example.utils.EntityManages;

public class UserDAOImpl extends BaseDAOImp<UsersModel, Long> implements UserDAO {
    public UserDAOImpl(){
        super(UsersModel.class);
    }
    EntityManages entityManages = new EntityManages();
    private EntityManager entityManager = entityManages.getEntityManager();



    @Override
    public UsersModel findByEmail(String email){
        try{
            return entityManager.createQuery("SELECT e FROM UsersModel e WHERE e.email = :email",UsersModel.class)
                    .setParameter("email",email)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
