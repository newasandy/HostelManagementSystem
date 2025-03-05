package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.utils.EntityManages;

import java.sql.Timestamp;
import java.util.List;

public class RoomAllocationDAOImp extends BaseDAOImp<RoomAllocation, Long> implements RoomAllocationDAO {

    private final EntityManages entityManages = new EntityManages();
    private final EntityManager entityManager = entityManages.getEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();
    public RoomAllocationDAOImp(){
        super(RoomAllocation.class);
    }

    @Override
    public Long getRoomOccupancy(Rooms roomId){
        return entityManager.createQuery(
                        "SELECT COUNT(ra) FROM RoomAllocation ra WHERE ra.roomId = :roomId AND ra.unallocationDate IS NULL", Long.class)
                .setParameter("roomId", roomId)
                .getSingleResult();
    }

    @Override
    public boolean unallocateStudent(Long studentId, Long roomId, Timestamp unallocationDate){
        try{
            entityTransaction.begin();
            int updateRow = entityManager.createQuery("UPDATE RoomAllocation ra SET ra.unallocationDate = :unallocationDate WHERE ra.studentId.id = :studentId AND ra.roomId.id = :roomId AND ra.unallocationDate IS NULL")
                    .setParameter("unallocationDate", unallocationDate)
                    .setParameter("studentId",studentId)
                    .setParameter("roomId",roomId)
                    .executeUpdate();
            entityTransaction.commit();

            return updateRow > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RoomAllocation> getUserAllocated(Long userId){
        try{
            return entityManager.createQuery("SELECT ra FROM RoomAllocation ra WHERE ra.studentId.id = :studentId",RoomAllocation.class)
                    .setParameter("studentId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean disableRoomUnallocatedStudent(Long roomId, Timestamp unallocationDate){
        try{
            entityTransaction.begin();
            int updateRow = entityManager.createQuery("UPDATE RoomAllocation ra SET ra.unallocationDate = :unallocationDate WHERE ra.roomId.id = :roomId AND ra.unallocationDate IS NULL")
                    .setParameter("unallocationDate", unallocationDate)
                    .setParameter("roomId",roomId)
                    .executeUpdate();
            entityTransaction.commit();

            return updateRow > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
