package org.example.controller;

import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.model.Visitors;
import org.example.service.LeaveRequestService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class LeaveRequestController {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService();
    Scanner sc = new Scanner(System.in);

    public void viewUserViewRequest(Users user){

    }

    public void applyLeaveRequestController(Users users){
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

    }
}
