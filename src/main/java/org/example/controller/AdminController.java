package org.example.controller;

import org.example.model.*;
import org.example.service.AdminService;
import org.example.utils.PasswordUtil;

import java.util.List;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class AdminController {
    private final Scanner sc = new Scanner(System.in);
    private final AdminService adminService = new AdminService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final LeaveRequestController leaveRequestController = new LeaveRequestController();
    private final RoomsController roomsController = new RoomsController();
    private final VisitorsController visitorsController = new VisitorsController();
    private final MonthyFeeController monthyFeeController = new MonthyFeeController();
    private Users admins = new Users();
    public void loginedAdminService(Users admin){
        admins = admin;
        while (true){
            System.out.println("1. View All Students");
            System.out.println("2. View All Rooms");
            System.out.println("3. View Allocated Details");
            System.out.println("4. View Visitors");
            System.out.println("5. View All Leave request");
            System.out.println("6. View Monthly Fee");
            System.out.println("7. logout");
            int inputs = sc.nextInt();
            if (inputs == 1){
                System.out.println("======================================");
                viewAllStudent();
                System.out.println("======================================");
            } else if (inputs == 2) {
                System.out.println("======================================");
                roomsController.viewAllRoom();
                System.out.println("======================================");
            } else if (inputs == 3) {
                System.out.println("======================================");
                roomsController.viewAllocatedDetails();
                System.out.println("======================================");
            } else if (inputs == 4) {
                System.out.println("======================================");
                visitorsController.viewVisitors();
            }else if (inputs == 5) {
                System.out.println("======================================");
                viewAllLeaveRequest();
            } else if (inputs ==6) {
                viewAllStudentFee();
            } else if (inputs ==7) {
                break;
            }
        }
    }

    public void viewAllStudent(){
        List<Users> allUser = adminService.viewOnlyStudent();
        System.out.printf("%-15s %-20s %-25s%n", "User Id", "Full Name", "Email");
        System.out.println("======================================================");
        for(Users student : allUser ){
            System.out.printf("%-15s %-20s %-25s%n",student.getId(),student.getFullName(),student.getEmail());
        }
        while (true){
            System.out.println("1. Add New Student");
            System.out.println("2. Update Users Details");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.println("=================================");
            int option = sc.nextInt();
            if (option == 1){
                addNewStudent();
            } else if (option == 2) {
                updateUsersDetails();
            } else if (option == 3) {
                deleteUser();
            } else if (option == 4) {
                break;
            }
        }
    }

    public void addNewStudent(){
        sc.nextLine();
        Users student = new Users();
        Address address = new Address();

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
        String rmcMc = sc.nextLine();
        System.out.println("Enter ward number");
        int wardNo = sc.nextInt();
        sc.nextLine();

        student.setFullName(studentName);
        student.setEmail(email);
        student.setPasswords(hashPassword);
        student.setRoles(role);
        student.setStatus(true);

        address.setCountry(country);
        address.setDistrict(district);
        address.setRmcMc(rmcMc);
        address.setWardNo(wardNo);

        statusMessageModel = adminService.registerNewStudent(student);
        if (statusMessageModel.isStatus()){
            address.setUser(student);
                if (adminService.addUserAddress(address)){
                    System.out.println(statusMessageModel.getMessage());
                }
        }else {
            System.out.println(statusMessageModel.getMessage());
        }
    }

    public void updateUsersDetails(){
        adminService.getAllUserAndAddress();
        System.out.println("Pick user by Row Number which want to update:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        Address user = adminService.getUserDetailByRowNumber(rowNumber);
        while (true){
            System.out.println("Which field want to update:");
            System.out.println("===============================");
            System.out.println("1. Name: "+user.getUser().getFullName());
            System.out.println("2. Email: "+user.getUser().getEmail());
            System.out.println("3. Role: "+user.getUser().getRoles());
            System.out.println("4. Status: "+(user.getUser().isStatus() ? "Active" : "Inactive"));
            System.out.println("5. Country: "+user.getCountry());
            System.out.println("6. District: "+user.getDistrict());
            System.out.println("7. RMC/MC: "+user.getRmcMc());
            System.out.println("8. Ward No: "+user.getWardNo());
            System.out.println("9. Update ");
            int option = sc.nextInt();
            if (option ==1){
                sc.nextLine();
                System.out.println("Enter new Name:");
                user.getUser().setFullName(sc.nextLine());
            } else if (option ==2) {
                sc.nextLine();
                System.out.println("Enter new Email:");
                user.getUser().setEmail(sc.nextLine());
            }else if (option ==3) {
                sc.nextLine();
                System.out.println("Enter new Role:");
                user.getUser().setRoles(sc.nextLine());
            }else if (option ==4) {
                sc.nextLine();
                System.out.println("Enter new status:1 for active and 0 or inactive");
                int status = sc.nextInt();
                if (status ==1){
                    user.getUser().setStatus(true);
                } else if (status == 0) {
                    user.getUser().setStatus(false);
                }
            }else if (option ==5) {
                sc.nextLine();
                System.out.println("Enter new Country:");
                user.setCountry(sc.nextLine());
            }else if (option ==6) {
                sc.nextLine();
                System.out.println("Enter new District:");
                user.setDistrict(sc.nextLine());
            }else if (option ==7) {
                sc.nextLine();
                System.out.println("Enter new RMC/MC:");
                user.setRmcMc(sc.nextLine());
            }else if (option ==8) {
                sc.nextLine();
                System.out.println("Enter new Ward No.:");
                user.setWardNo(sc.nextInt());
            }else if (option ==9) {
                break;
            }
        }
        statusMessageModel = adminService.updateUserDetails(user);
        System.out.println(statusMessageModel.getMessage());
    }

    public void deleteUser(){
        adminService.getAllUserAndAddress();
        System.out.println("Pick user by Row Number which want to delete:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        statusMessageModel = adminService.deleteUserService(rowNumber);
        System.out.println(statusMessageModel.getMessage());
    }

    public void viewAllLeaveRequest(){
        leaveRequestController.viewAllLeaveRequestByAdmin();
        while (true){
            System.out.println("1. Response Pending Leave Request");
            System.out.println("2. Exit");
            int option = sc.nextInt();
            if (option == 1){
                leaveRequestController.responseLeaveRequestByAdmin();
            } else if (option ==2) {
                break;
            }
        }
    }

    public void viewAllStudentFee(){
        monthyFeeController.viewAllStudentFee();
        System.out.println("============================");
        System.out.println("1. Assign New Monthly Fee");
        System.out.println("2. exit");
        int option = sc.nextInt();
        if (option == 1){
            monthyFeeController.assignMonthlyFee();
        }

    }
}
