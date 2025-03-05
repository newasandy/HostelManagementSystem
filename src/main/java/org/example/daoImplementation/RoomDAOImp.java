package org.example.daoImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.daoInterface.RoomDAO;
import org.example.model.Rooms;
import org.example.utils.EntityManages;


public class RoomDAOImp extends BaseDAOImp<Rooms, Long> implements RoomDAO {

    EntityManages entityManages = new EntityManages();
    private EntityManager entityManager = entityManages.getEntityManager();

    public RoomDAOImp (){
        super(Rooms.class);
    }

    public Rooms findByRoomNumber(int roomNumber){
        try{
            return entityManager.createQuery("SELECT r FORM Rooms r WHERE r.roomNumber = : roomNumber", Rooms.class)
                    .setParameter("roomNumber", roomNumber)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
