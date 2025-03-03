package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.daoInterface.RoomAloocationDAO;
import org.example.model.RoomAllocationModel;
import org.example.utils.EntityManages;

import java.sql.Timestamp;
import java.util.List;

public class RoomAllocationDAOImp extends BaseDAOImp<RoomAllocationModel , Long> implements RoomAloocationDAO {

    private final EntityManages entityManages = new EntityManages();
    private final EntityManager entityManager = entityManages.getEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();
    public RoomAllocationDAOImp(){
        super(RoomAllocationModel.class);
    }

    @Override
    public Long getRoomOccupancy(Long roomId){
        return entityManager.createQuery(
                        "SELECT COUNT(ra) FROM RoomAllocationModel ra WHERE ra.room_id = :room_id AND ra.unallocation_date IS NULL", Long.class)
                .setParameter("room_id", roomId)
                .getSingleResult();
    }

    @Override
    public boolean unallocateStudent(Long studentId, Long roomId, Timestamp unallocationDate){
        try{
            entityTransaction.begin();
            int updateRow = entityManager.createQuery("UPDATE RoomAllocationModel ra SET ra.unallocation_date = :unallocationDate WHERE ra.student_id = :studentId AND ra.room_id = :roomId AND ra.unallocation_date IS NULL")
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
}
