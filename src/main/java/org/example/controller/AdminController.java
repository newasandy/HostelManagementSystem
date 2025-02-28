package org.example.controller;

import org.example.model.AddressModel;
import org.example.model.UsersModel;
import org.example.service.AdminService;
import org.example.utils.PasswordUtil;

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
        AddressModel address = new AddressModel();

        System.out.println("Enter full name");
        String studentName = sc.nextLine();
        System.out.println("Enter Email");
        String email= sc.nextLine();
        System.out.println("Enter Password");
        String plain_password= sc.nextLine();
        System.out.println("Role");
        String role = sc.nextLine();
        String hashPassword = PasswordUtil.getHashPassword(plain_password);

        System.out.println("Enter Country");
        String country = sc.nextLine();
        System.out.println("Enter District");
        String district = sc.nextLine();
        System.out.println("Enter metropolitan city / municipality / rural municipality");
        String rmc_mc = sc.nextLine();
        System.out.println("Enter ward number");
        int ward_no = sc.nextInt();
        sc.nextLine();

        student.setFull_name(studentName);
        student.setEmail(email);
        student.setPasswords(hashPassword);
        student.setRoles(role);
        student.setStatus(true);

        address.setCountry(country);
        address.setDistrict(district);
        address.setRmc_mc(rmc_mc);
        address.setWard_no(ward_no);

        if (adminService.registerNewStudent(student)){
            address.setUser_id(student.getId());
                if (adminService.addUserAddress(address)){
                    System.out.println("Register Success");
                }
        }else {
            System.out.println("register not success");
        }


    }
}
