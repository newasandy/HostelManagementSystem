package org.example.controller;

import org.example.model.UsersModel;
import org.example.service.AdminService;

import java.util.Scanner;

public class AdminController {
    private Scanner sc = new Scanner(System.in);
    private boolean isAdmin ;
    private AdminService adminService = new AdminService();
    public void adminLogin(){


        UsersModel admin = new UsersModel();
        while (true){
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            admin.setEmail(email);
            admin.setPasswords(password);
            isAdmin = adminService.adminLogin(admin);
            if(isAdmin){
                System.out.println("login success");
                loginedAdminService();
                break;
            }else{
                System.out.println("1. Re-Enter");
                System.out.println("2. Exit");
                String inpute = sc.nextLine();
                if (inpute.equals("2")){
                    break;
                }
            }
        }

    }
    private void loginedAdminService(){
        while (true){
            System.out.println("1. Add Student");
            System.out.println("2. Student Allocate at Room");
            System.out.println("3. Add Room");
            System.out.println("4. Update Room Allocation");
            System.out.println("5. logout");
            int inputs = sc.nextInt();
            if (inputs == 1){
                registerStudent();
            } else if (inputs == 2) {
                System.out.println("allocated student");
            } else if (inputs == 3) {
                System.out.println("add room");
            } else if (inputs == 4) {
                System.out.println("update rooms allocations");
            } else if (inputs ==5) {
                break;
            }
        }
    }
    private void registerStudent(){
        sc.nextLine();
        UsersModel student = new UsersModel();
        System.out.println("Enter full name");
        String studentName = sc.nextLine();
        System.out.println("Enter Email");
        String email= sc.nextLine();
        System.out.println("Enter Password");
        String password= sc.nextLine();
        System.out.println("Role");
        String role = sc.nextLine();

        student.setFull_name(studentName);
        student.setEmail(email);
        student.setPasswords(password);
        student.setRoles(role);
        student.setStatus(true);

        if (adminService.registerNewStudent(student)){
            System.out.println("register successfully");
        }else {
            System.out.println("register not success");
        }


    }
}
