package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.RoomDAO;
import org.example.model.Rooms;
import org.example.utils.EntityManages;

import java.util.List;


public class RoomDAOImp extends BaseDAOImp<Rooms, Long> implements RoomDAO {

    EntityManages entityManages = new EntityManages();
    private EntityManager entityManager = entityManages.getEntityManager();

    public RoomDAOImp (){
        super(Rooms.class);
    }

    @Override
    public Rooms findByRoomNumber(int roomNumber){
        try{
            return entityManager.createQuery("SELECT r FROM Rooms r WHERE r.roomNumber = :roomNumber",Rooms.class)
                    .setParameter("roomNumber", roomNumber)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Rooms> getAvailableRoom(){
        try{
            return entityManager.createQuery("SELECT r FROM Rooms r WHERE r.status = TRUE AND r.capacity > "+ "(SELECT COUNT(ra) FROM RoomAllocation ra WHERE ra.roomId.id = r.id AND ra.unallocationDate IS NULL)",Rooms.class)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
