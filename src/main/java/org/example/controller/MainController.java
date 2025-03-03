package org.example.controller;

import org.example.daoImplementation.UserDAOImpl;
import org.example.model.Users;
import org.example.testConnection.TestConnection;

import java.util.List;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminController adminController = new AdminController();
        TestConnection tc = new TestConnection();
        MainController mcc = new MainController();
        while (true){
            System.out.println("Select user: \n 1. Admin \n 2. Student \n 3. Exit");
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1){
                adminController.adminLogin();
            } else if (i == 2){
                mcc.testGetAllUser();
            }else if (i ==3) {
                System.out.println("have a good day");
                break;
            }
        }

    }

    public void testGetAllUser(){

        UserDAOImpl getAll = new UserDAOImpl();


        List<Users> userss = getAll.getAll();
        for (Users usersModel:userss){
            System.out.println("ID: "+usersModel.getId() +"Name: "+usersModel.getFullName()+" email: "+usersModel.getEmail()+" password: "+usersModel.getPasswords()+" roll: "+usersModel.getRoles()+" status: "+usersModel.isStatus());
        }
    }
}