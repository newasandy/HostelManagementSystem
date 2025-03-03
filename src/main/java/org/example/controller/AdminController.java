package org.example.controller;

import org.example.model.*;
import org.example.service.AdminService;
import org.example.utils.PasswordUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class AdminController {
    private Scanner sc = new Scanner(System.in);
    private final AdminService adminService = new AdminService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();

    public void adminLogin(){


        UsersModel admin = new UsersModel();
        while (true){
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            admin.setEmail(email);
            admin.setPasswords(password);

            statusMessageModel = adminService.adminLoginService(admin);
            if(statusMessageModel.isStatus()){
                System.out.println(statusMessageModel.getMessage());
                loginedAdminService();
                break;
            }else{
                System.out.println(statusMessageModel.getMessage());
                System.out.println("1. Re-Enter");
                System.out.println("2. Exit");
                String option = sc.nextLine();
                if (option.equals("2")){
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
            System.out.println("4. View All User");
            System.out.println("5. Student Allocate at Room");
            System.out.println("6. Update Room Allocation");
            System.out.println("7. logout");
            int inputs = sc.nextInt();
            if (inputs == 1){
                System.out.println("======================================");
                registerStudent();
                System.out.println("======================================");
            } else if (inputs == 2) {
                System.out.println("======================================");
                addNewRoom();
                System.out.println("======================================");
            } else if (inputs == 3) {
                System.out.println("======================================");
                adminService.getAllUser();
                System.out.println("======================================");
            } else if (inputs == 4) {
                System.out.println("======================================");
                adminService.getAllRoom();
                System.out.println("======================================");
            } else if (inputs ==5) {
                System.out.println("======================================");
                allocatedRoom();
            }else if (inputs ==7) {
                break;
            }
        }
    }
    public void registerStudent(){
        sc.nextLine();
        UsersModel student = new UsersModel();
        AddressModel address = new AddressModel();
//        StatusMessageModel statusMessageModel = new StatusMessageModel();

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

    public void allocatedRoom(){
        RoomAllocationModel roomAllocationModel = new RoomAllocationModel();
        adminService.viewUnalicatedStudent();
        System.out.println("Select student by row number ");
        int studentRowNumber = sc.nextInt();
        Long studentId = adminService.getUserIdByRowNumber(studentRowNumber);
        int roomRowNumber;
        Long roomId ;
        while (true){
            adminService.getAllRoom();
            System.out.println("Select Room by row Number ");
            roomRowNumber = sc.nextInt();
            int roomCapacity = adminService.getRoomCapacity(roomRowNumber);
            roomId = adminService.getRoomIdByRowNumber(roomRowNumber);
            if (adminService.isRoomAvailable(roomId,roomCapacity)){
                break;
            }
            System.out.println("Selected Room Is full select other room");

        }


        Date getDate = new Date();
        Timestamp allocationDate = new Timestamp(getDate.getTime());

        roomAllocationModel.setStudent_id(studentId);
        roomAllocationModel.setRoom_id(roomId);
        roomAllocationModel.setAllocation_date(allocationDate);

        statusMessageModel = adminService.setStudentAtRoom(roomAllocationModel);
        System.out.println(statusMessageModel.getMessage());

    }


}
