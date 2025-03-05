package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.utils.EntityManages;

import java.util.List;


public class LeaveRequestDAOImp extends BaseDAOImp<LeaveRequest, Long> implements LeaveRequestDAO {
    private final EntityManages entityManages = new EntityManages();
    private final EntityManager entityManager = entityManages.getEntityManager();


    public LeaveRequestDAOImp(){
        super(LeaveRequest.class);
    }


    @Override
    public List<LeaveRequest> getUserLeaveRequestByUserId(Long userId){
        try{
            return entityManager.createQuery("SELECT lr FROM LeaveRequest lr WHERE lr.studentId.id = :studentId", LeaveRequest.class)
                    .setParameter("studentId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public LeaveRequest checkLeaveRequest(Long userId){
        try{
            return entityManager.createQuery("SELECT lr FROM LeaveRequest lr WHERE lr.studentId.id = :studentId and lr.status = :status", LeaveRequest.class)
                    .setParameter("studentId",userId)
                    .setParameter("status", "PENDING")
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
