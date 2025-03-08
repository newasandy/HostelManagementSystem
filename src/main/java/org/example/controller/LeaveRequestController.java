package org.example.controller;

import org.example.daoImplementation.LeaveRequestDAOImp;
import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.LeaveRequestService;

import javax.swing.text.AbstractDocument;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LeaveRequestController {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAOImp();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestDAO);
    Scanner sc = new Scanner(System.in);

    public void getUserLeaveRequest(Users user){
        List<LeaveRequest> leaveRequestList = leaveRequestService.viewUserLeaveRequestByUser(user.getId());
        for (LeaveRequest leaveRequest : leaveRequestList){
            System.out.println(leaveRequest.getApplyDate()+"\t\t"+leaveRequest.getReason()+"\t\t"+leaveRequest.getStartFrom()+"\t\t"+leaveRequest.getLeaveDays()+"\t\t"+leaveRequest.getStatus());
        }
    }

    public LeaveRequest getPendingLeaveRequest(Long userId){
        return leaveRequestDAO.checkLeaveRequest(userId);
    }

    public void getAllPendingLeaveRequest(){
        List<LeaveRequest> leaveRequestList =  leaveRequestDAO.getAllPendingRequest();
        for (LeaveRequest lr : leaveRequestList){
            System.out.println(lr.getStudentId().getFullName()+"\t\t\t"+lr.getApplyDate()+"\t\t\t"+lr.getReason()+"\t\t\t"+lr.getStartFrom()+"\t\t\t"+lr.getLeaveDays()+"\t\t"+lr.getStatus());
        }
    }

    public LeaveRequest getLeaveRequestByRowNumber(int rowNumber){
        List<LeaveRequest> leaveRequestList = leaveRequestDAO.getAllPendingRequest();
        if (rowNumber <=0 || rowNumber > leaveRequestList.size()){
            System.out.println("Invalid Row Number");
            return null;
        }else {
            return leaveRequestList.get(rowNumber-1);
        }
    }

    public StatusMessageModel checkLeaveRequest(Long userId){
        LeaveRequest lr = leaveRequestDAO.checkLeaveRequest(userId);
        if (lr == null){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("No Leave Request");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("Leave Request Already Exist and Still Pending");
        }
        return statusMessageModel;
    }

    public StatusMessageModel applyLeaveRequestController(Users users, String reason, String startFrom, String leaveDays, Timestamp applyDate){
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStudentId(users);
        leaveRequest.setReason(reason);
        leaveRequest.setStartFrom(startFrom);
        leaveRequest.setLeaveDays(leaveDays);
        leaveRequest.setApplyDate(applyDate);
        leaveRequest.setStatus("PENDING");

        return leaveRequestService.applyLeaveRequestService(leaveRequest);
    }


    public StatusMessageModel updatePendingLeaveRequest(LeaveRequest leaveRequest){
        return leaveRequestService.updateLeaveRequest(leaveRequest);
    }

    public void viewAllLeaveRequestByAdmin(){
        List<LeaveRequest> leaveRequestList = leaveRequestDAO.getAll();
        for (LeaveRequest lr : leaveRequestList){
            System.out.println(lr.getStudentId().getFullName()+"\t\t\t"+lr.getApplyDate()+"\t\t\t"+lr.getReason()+"\t\t\t"+lr.getStartFrom()+"\t\t\t"+lr.getLeaveDays()+"\t\t"+lr.getStatus());
        }
    }

    public void responseLeaveRequestByAdmin(){
        getAllPendingLeaveRequest();
        LeaveRequest updateLeaveRequest;
        System.out.println("Select leave request by row number");
        int rowNumber = sc.nextInt();
        updateLeaveRequest = getLeaveRequestByRowNumber(rowNumber);
        System.out.println("====================================");
        System.out.println(updateLeaveRequest.getStudentId().getFullName()+"\t\t\t"+updateLeaveRequest.getApplyDate()+"\t\t\t"+updateLeaveRequest.getReason()+"\t\t\t"+updateLeaveRequest.getStartFrom()+"\t\t\t"+updateLeaveRequest.getLeaveDays()+"\t\t"+updateLeaveRequest.getStatus());
        System.out.println("=============================");
        System.out.println("1. Do Accept");
        System.out.println("2. Do Reject");
        System.out.println("3. Exit");
        int option =sc.nextInt();
        if (option == 1){
            updateLeaveRequest.setStatus("ACCEPTED");
            statusMessageModel= leaveRequestService.updateLeaveRequest(updateLeaveRequest);
            System.out.println(statusMessageModel.getMessage());
        } else if (option == 2) {
            updateLeaveRequest.setStatus("REJECTED");
            statusMessageModel= leaveRequestService.updateLeaveRequest(updateLeaveRequest);
            System.out.println(statusMessageModel.getMessage());
        }
    }
}
