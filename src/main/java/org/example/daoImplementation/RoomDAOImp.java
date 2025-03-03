package org.example.daoImplementation;

import org.example.model.Rooms;

public class RoomDAOImp extends BaseDAOImp<Rooms, Long> {
    public RoomDAOImp (){
        super(Rooms.class);
    }
}
