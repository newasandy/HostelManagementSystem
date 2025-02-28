package org.example.service;

import org.example.dao.BaseDAO;
import org.example.dao.UserDAO;
import org.example.dao.UserDAOImp;
import org.example.daoImpl.BaseDAOImp;
import org.example.daoImpl.UserDAOImpl;
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
//        UsersModel finduser = user.getById(registerStudent.getId());
//        System.out.println("new User added");
        return user.add(registerStudent);
    }

}
