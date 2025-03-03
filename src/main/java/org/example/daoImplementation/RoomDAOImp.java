package org.example.daoImplementation;

import org.example.model.RoomModel;

public class RoomDAOImp extends BaseDAOImp<RoomModel , Long> {
    public RoomDAOImp (){
        super(RoomModel.class);
    }
}
