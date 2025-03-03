package org.example.controller;

import org.example.model.AddressModel;
import org.example.model.RoomModel;
import org.example.model.StatusMessageModel;
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
            isAdmin = adminService.adminLoginService(admin);
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
    public void loginedAdminService(){
        while (true){
            System.out.println("1. Add Student");
            System.out.println("2. Add New Room");
            System.out.println("3. View All User");
            System.out.println("4. Student Allocate at Room");
            System.out.println("5. Update Room Allocation");
            System.out.println("6. logout");
            int inputs = sc.nextInt();
            if (inputs == 1){
                registerStudent();
            } else if (inputs == 2) {
                addNewRoom();
            } else if (inputs == 3) {
                System.out.println("add room");
            } else if (inputs == 4) {
                System.out.println("update rooms allocations");
            } else if (inputs ==6) {
                break;
            }
        }
    }
    public void registerStudent(){
        sc.nextLine();
        UsersModel student = new UsersModel();
        AddressModel address = new AddressModel();
        StatusMessageModel statusMessageModel = new StatusMessageModel();

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


        statusMessageModel = adminService.registerNewStudent(student);

        if (statusMessageModel.isStatus()){
            address.setUser_id(student.getId());
                if (adminService.addUserAddress(address)){
                    System.out.println(statusMessageModel.getMessage());
                }
        }else {
            System.out.println(statusMessageModel.getMessage());
        }

    }

    public void addNewRoom(){
        sc.nextLine();
        StatusMessageModel statusMessageModel = new StatusMessageModel();
        RoomModel roomModel = new RoomModel();

        System.out.println("Enter New Room Number");
        int roomNumber = sc.nextInt();
        System.out.println("Enter Room Capacity");
        int roomCapacity = sc.nextInt();

        roomModel.setRoom_number(roomNumber);
        roomModel.setCapacity(roomCapacity);

        statusMessageModel = adminService.addNewRoomService(roomModel);
        if (statusMessageModel.isStatus()){
            System.out.println(statusMessageModel.getMessage());
        }else {
            System.out.println(statusMessageModel.getMessage());
        }

    }

}
