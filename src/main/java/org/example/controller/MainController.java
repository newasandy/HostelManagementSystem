package org.example.controller;

import org.example.testConnection.TestConnection;

import java.util.Date;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminController adminController = new AdminController();
        TestConnection tc = new TestConnection();
        while (true){
            System.out.println("Select user: \n 1. Admin \n 2. Student \n 3. Exit");
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1){
                adminController.adminLogin();
            } else if (i == 2){
//                System.out.println("this is student");
                tc.testConnection();
            }else if (i ==3) {
                System.out.println("have a good day");
                break;
            }
        }

    }
}