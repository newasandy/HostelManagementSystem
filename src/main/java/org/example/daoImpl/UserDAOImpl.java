package org.example.daoImpl;

import org.example.model.UsersModel;

public class UserDAOImpl extends BaseDAOImp<UsersModel, Long>{
    public UserDAOImpl(){
        super(UsersModel.class);
    }
}
