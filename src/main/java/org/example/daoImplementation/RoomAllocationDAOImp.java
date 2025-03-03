package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import org.example.daoInterface.RoomAloocationDAO;
import org.example.model.RoomAllocationModel;
import org.example.utils.EntityManages;

import java.util.List;

public class RoomAllocationDAOImp extends BaseDAOImp<RoomAllocationModel , Long> implements RoomAloocationDAO {

    private final EntityManages entityManages = new EntityManages();
    private final EntityManager entityManager = entityManages.getEntityManager();

    public RoomAllocationDAOImp(){
        super(RoomAllocationModel.class);
    }

    @Override
    public Long getRoomOccupancy(Long roomId){
        return entityManager.createQuery(
                        "SELECT COUNT(ra) FROM RoomAllocationModel ra WHERE ra.room_id = :room_id", Long.class)
                .setParameter("room_id", roomId)
                .getSingleResult();
    }
}
