package org.example.service;

import org.example.dao.UserDAO;
import org.example.dao.UserDAOImp;
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

        UserDAO userDAO = new UserDAOImp();
        userDAO.addUser(registerStudent);
//        System.out.println("Name: "+registerStudent.getFullName()+"email: "+registerStudent.getEmail()+"password: "+registerStudent.getPasswords()+"roles: "+ registerStudent.getRoles());
        return true;
    }

}
