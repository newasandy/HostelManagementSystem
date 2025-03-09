package org.example.service;

import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;

import java.util.List;

public class LeaveRequestService {
    private LeaveRequestDAO leaveRequestDAO;
    private StatusMessageModel statusMessageModel = new StatusMessageModel();

    public LeaveRequestService(LeaveRequestDAO leaveRequestDAO){
        this.leaveRequestDAO = leaveRequestDAO;
    }

    public List<LeaveRequest> viewUserLeaveRequestByUser(Long userId){
        return leaveRequestDAO.getUserLeaveRequestByUserId(userId);
    }


    public StatusMessageModel applyLeaveRequestService(LeaveRequest leaveRequest){
        if (leaveRequestDAO.add(leaveRequest)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Leave Application is Submit Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Leave Application is not Submit");
        }
        return statusMessageModel;
    }

    public StatusMessageModel updateLeaveRequest(LeaveRequest leaveRequest){
        if (leaveRequestDAO.update(leaveRequest)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Leave Request Update Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("Leave Request not Update");
        }
        return statusMessageModel;
    }



}
