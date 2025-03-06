package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.MonthlyFeeDAO;
import org.example.model.LeaveRequest;
import org.example.model.MonthlyFee;
import org.example.utils.EntityManages;

import java.util.List;

public class MonthlyFeeDAOImpl extends BaseDAOImp<MonthlyFee,Long> implements MonthlyFeeDAO {

    EntityManages entityManages = new EntityManages();
    private EntityManager entityManager = entityManages.getEntityManager();

    public MonthlyFeeDAOImpl(){
        super(MonthlyFee.class);
    }

    @Override
    public List<MonthlyFee> getUserFeeDetails(Long userId){
        try{
            return entityManager.createQuery("SELECT m FROM MonthlyFee m WHERE m.studentId.id = :studentId", MonthlyFee.class)
                    .setParameter("studentId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<MonthlyFee> getUserUnPaidFee(Long userId){
        try{
            return entityManager.createQuery("SELECT m FROM MonthlyFee m WHERE m.studentId.id = :studentId AND m.paid < m.feeAmount", MonthlyFee.class)
                    .setParameter("studentId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<MonthlyFee> getAllUserUnPaidFee(){
        try{
            return entityManager.createQuery("SELECT m FROM MonthlyFee m WHERE m.paid < m.feeAmount", MonthlyFee.class)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
