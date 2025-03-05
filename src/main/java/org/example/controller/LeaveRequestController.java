package org.example.controller;

import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.LeaveRequestService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LeaveRequestController {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService();
    Scanner sc = new Scanner(System.in);

    public void viewUserViewRequest(Users user){
        List<LeaveRequest> leaveRequestList = leaveRequestService.viewUserLeaveRequestByUser(user.getId());
        for (LeaveRequest leaveRequest : leaveRequestList){
            System.out.println(leaveRequest.getApplyDate()+"\t\t"+leaveRequest.getReason()+"\t\t"+leaveRequest.getStartFrom()+"\t\t"+leaveRequest.getLeaveDays()+"\t\t"+leaveRequest.getStatus());
        }
        System.out.println("============================================");
        System.out.println("\t 1. Apply Leave Request");
        System.out.println("\t 2. Update Pending Leave Request");
        System.out.println("\t 3. Exit");
        int option = sc.nextInt();
        if (option == 1){
            applyLeaveRequestController(user);
        } else if (option == 2) {
            updatePendingLeaveRequest(user);
        }
    }

    public void applyLeaveRequestController(Users users){
        statusMessageModel = leaveRequestService.checkLeaveRequest(users.getId());
        if (statusMessageModel.isStatus()){
            LeaveRequest leaveRequest = new LeaveRequest();
            System.out.println("Enter Reason");
            String reason = sc.nextLine();
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
            sc.nextLine();
            System.out.println("Enter how many day/s leave:");
            String leaveDay = sc.nextLine();
            Date date = new Date();
            Timestamp applyDate = new Timestamp(date.getTime());

            leaveRequest.setApplyDate(applyDate);
            leaveRequest.setStartFrom(startDays);
            leaveRequest.setLeaveDays(leaveDay);
            leaveRequest.setReason(reason);
            leaveRequest.setStatus("PENDING");
            leaveRequest.setStudentId(users);

            statusMessageModel = leaveRequestService.applyLeaveRequestService(leaveRequest);
            System.out.println(statusMessageModel.getMessage());
        }else {
            System.out.println(statusMessageModel.getMessage());
        }
    }

    public void updatePendingLeaveRequest(Users users){
        System.out.println("Select leave request by enter row number:");
        int rowNumber = sc.nextInt();
        LeaveRequest updateLeaveRequest = leaveRequestService.getLeaveDetailsByRowNumber(rowNumber,users.getId());
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
            statusMessageModel = leaveRequestService.updateLeaveRequest(updateLeaveRequest);
            System.out.println(statusMessageModel.getMessage());
        }else {
            System.out.println("Selected Leave Request is Not In Pending.");
        }
    }
}
