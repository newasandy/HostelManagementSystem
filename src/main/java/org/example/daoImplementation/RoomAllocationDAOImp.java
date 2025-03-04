package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.Users;
import org.example.utils.EntityManages;

import java.sql.Timestamp;

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
    public boolean unallocateStudent(Users studentId, Rooms roomId, Timestamp unallocationDate){
        try{
            entityTransaction.begin();
            int updateRow = entityManager.createQuery("UPDATE RoomAllocation ra SET ra.unallocationDate = :unallocationDate WHERE ra.studentId = :studentId AND ra.roomId = :roomId AND ra.unallocationDate IS NULL")
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
    public boolean unallocatedStudentBeforeDeleteRoom(Rooms roomId, Timestamp unallocationDate){
        try{
            entityTransaction.begin();
            int updateRow = entityManager.createQuery("UPDATE RoomAllocation ra SET ra.unallocationDate = :unallocationDate WHERE ra.roomId = :roomId AND ra.unallocationDate IS NULL")
                    .setParameter("roomId", roomId)
                    .setParameter("unallocationDate", unallocationDate)
                    .executeUpdate();
            entityTransaction.commit();
            return updateRow >0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
