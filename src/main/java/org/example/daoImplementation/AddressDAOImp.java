package org.example.daoImplementation;

import org.example.model.AddressModel;

public class AddressDAOImp extends BaseDAOImp<AddressModel,Long>{
    public AddressDAOImp (){
        super(AddressModel.class);
    }
}
