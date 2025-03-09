package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.VisitorsDAO;
import org.example.model.Visitors;
import org.example.utils.EntityManages;

import java.util.List;

public class VisitorsDAOImp extends BaseDAOImp<Visitors , Long> implements VisitorsDAO {

    EntityManages entityManages = new EntityManages();
    private EntityManager entityManager = entityManages.getEntityManager();
    public VisitorsDAOImp(){
        super(Visitors.class);
    }

    @Override
    public List<Visitors> getUserVisitedBy(Long userId){
        try{
            return entityManager.createQuery("SELECT v FROM Visitors v WHERE v.studentId.id = :studentId",Visitors.class)
                    .setParameter("studentId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Visitors> getAllNotExitVistior(){
        try{
            return entityManager.createQuery("SELECT v FROM Visitors v WHERE v.exitDatetime IS NULL",Visitors.class)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

}
