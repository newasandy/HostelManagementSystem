package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.UserDAO;
import org.example.model.UsersModel;
import org.example.utils.EntityManages;

import java.util.List;

public class UserDAOImpl extends BaseDAOImp<UsersModel, Long> implements UserDAO {
    public UserDAOImpl(){
        super(UsersModel.class);
    }
    private final EntityManages entityManages = new EntityManages();
    private final EntityManager entityManager = entityManages.getEntityManager();



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

    @Override
    public List<UsersModel> getUnallocatedUsers(){
        try{
            return entityManager.createQuery("SELECT e FROM UsersModel e WHERE e.roles = :roles AND e.id NOT IN (SELECT ra.student_id FROM RoomAllocationModel ra)",UsersModel.class)
                    .setParameter("roles", "USER")
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
