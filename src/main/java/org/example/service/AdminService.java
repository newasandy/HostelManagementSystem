package org.example.service;

import org.example.daoImplementation.AddressDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.model.AddressModel;
import org.example.model.UsersModel;

public class AdminService {
private boolean status;
    public boolean adminLogin(UsersModel admin){

        System.out.println("Email : "+ admin.getEmail()+ "Password: "+ admin.getPasswords());
    if (true){
        status = true;
    }else {
        status = false;
    }
        return status;
    }

    public boolean registerNewStudent(UsersModel registerStudent){

        UserDAOImpl user = new UserDAOImpl();
        return user.add(registerStudent);
    }

    public boolean addUserAddress(AddressModel address){
        AddressDAOImp addressAdd = new AddressDAOImp();
        return addressAdd.add(address);
    }

}
