package org.example.controller;

import org.example.daoImplementation.LeaveRequestDAOImp;
import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.LeaveRequestService;

import java.sql.Timestamp;
import java.util.List;

public class LeaveRequestController {
    private LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAOImp();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestDAO);

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
        int sn =1;
        for (LeaveRequest lr : leaveRequestList){
            System.out.println(sn +"\t\t"+lr.getStudentId().getFullName()+"\t\t\t"+lr.getApplyDate()+"\t\t\t"+lr.getReason()+"\t\t\t"+lr.getStartFrom()+"\t\t\t"+lr.getLeaveDays()+"\t\t"+lr.getStatus());
            sn++;
        }
    }

    public LeaveRequest getPendingLeaveRequestByRowNumber(int rowNumber){
        List<LeaveRequest> leaveRequestList = leaveRequestDAO.getAllPendingRequest();
        if (rowNumber <=0 || rowNumber > leaveRequestList.size()){
            System.out.println("Invalid Row Number");
            return null;
        }else {
            return leaveRequestList.get(rowNumber-1);
        }
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
}
