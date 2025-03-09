package org.example.view;

import org.example.controller.LeaveRequestController;
import org.example.controller.MonthyFeeController;
import org.example.controller.RoomsController;
import org.example.controller.VisitorsController;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;
import org.example.model.Users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class GeneralUserView {
    Scanner sc = new Scanner(System.in);
    private StatusMessageModel statusMessageModel = new StatusMessageModel();


    private final RoomsController roomsController = new RoomsController();
    private final VisitorsController visitorsController = new VisitorsController();
    private final LeaveRequestController leaveRequestController = new LeaveRequestController();
    private final MonthyFeeController monthyFeeController = new MonthyFeeController();
    public void userLoginService(Users loginUser){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1. View Allocated Room");
            System.out.println("2. Visited By");
            System.out.println("3. View Leave Request");
            System.out.println("4. View Fee Details");
            System.out.println("5. Logout");
            int option = sc.nextInt();
            if (option ==1){
                viewUserAllocatedDetails(loginUser);
            } else if (option == 2) {
                viewUserVisitedBy(loginUser);
            } else if (option == 3) {
                viewUserLeaveRequest(loginUser);
            } else if (option == 4) {
                viewFeeDetailsByUser(loginUser);
            } else if (option == 5) {
                break;
            }

        }

    }

    public void viewUserAllocatedDetails(Users user){
        roomsController.getUserAllocatedDetails(user);
    }

    public void viewUserVisitedBy(Users user){
        visitorsController.getUserVisitedBy(user);
    }

    public void viewUserLeaveRequest(Users user){
        leaveRequestController.getUserLeaveRequest(user);
        System.out.println("============================================");
        System.out.println("\t 1. Apply Leave Request");
        System.out.println("\t 2. Update Pending Leave Request");
        System.out.println("\t 3. Exit");
        int option = sc.nextInt();
        if (option == 1){
            applyLeaveRequest(user);
        } else if (option == 2) {
            updatePendingLeaveRequest(user);
        }
    }

    public void applyLeaveRequest(Users user){
        LeaveRequest leaveRequest = leaveRequestController.getPendingLeaveRequest(user.getId());
        if (leaveRequest == null){
            sc.nextLine();
            System.out.println("Enter Reason");
            String reason = sc.nextLine();
            System.out.println("From Which Days:");
            String startFrom ="";
            System.out.println("\t1. From Today");
            System.out.println("\t2. From Tomorrow");
            System.out.println("\t2. From After ? Days:");
            int daysOption = sc.nextInt();
            if (daysOption == 1){
                startFrom = "From today";
            } else if (daysOption == 2) {
                startFrom = "From tomorrow";
            } else if (daysOption == 3) {

                System.out.println("\t  Enter leave starting after ? days :");
                int day = sc.nextInt();
                startFrom = "After "+day+" day";
            }
            sc.nextLine();
            System.out.println("Enter how many day/s leave:");
            String leaveDay = sc.nextLine();
            Date date = new Date();
            Timestamp applyDate = new Timestamp(date.getTime());

            statusMessageModel = leaveRequestController.applyLeaveRequestController(user,reason, startFrom,leaveDay,applyDate);
            System.out.println(statusMessageModel.getMessage());

        }else {
            System.out.println("Leave Request Already Exist and Still Pending");
        }
    }

    public void updatePendingLeaveRequest(Users user){
        System.out.println("Update Only Pending Leave Request: ");
        LeaveRequest updateLeaveRequest = leaveRequestController.getPendingLeaveRequest(user.getId());
        if (updateLeaveRequest != null){
            System.out.println("1. Reason: "+updateLeaveRequest.getReason());
            System.out.println("2. Start from: "+updateLeaveRequest.getStartFrom());
            System.out.println("3. Leave Day: "+ updateLeaveRequest.getLeaveDays());
            int option = sc.nextInt();
            if (option == 1){
                sc.nextLine();
                System.out.println("\tEnter Reason");
                String reason = sc.nextLine();
                updateLeaveRequest.setReason(reason);
            } else if (option == 2) {
                sc.nextLine();
                System.out.println("From Which Days:");
                String startDays ="";
                System.out.println("\t1. From Today");
                System.out.println("\t2. From Tomorrow");
                System.out.println("\t2. From After ? Days:");
                int daysOption = sc.nextInt();
                if (daysOption == 1){
                    startDays = "From today";
                } else if (daysOption == 2) {
                    startDays = "From tomorrow";
                } else if (daysOption == 3) {

                    System.out.println("\t  Enter leave starting from :");
                    int day = sc.nextInt();
                    startDays = "After "+day+" day";
                }
                updateLeaveRequest.setStartFrom(startDays);
            } else if (option == 3) {
                sc.nextLine();
                System.out.println("\tEnter how many day/s leave:");
                String leaveDay = sc.nextLine();
                updateLeaveRequest.setLeaveDays(leaveDay);
            }

            statusMessageModel = leaveRequestController.updatePendingLeaveRequest(updateLeaveRequest);
            System.out.println(statusMessageModel.getMessage());
        }else {
            System.out.println("There is no Pending Leave Request To Update");
        }
    }

    public void viewFeeDetailsByUser(Users user){
        monthyFeeController.viewFeeByUser(user);
    }
}
