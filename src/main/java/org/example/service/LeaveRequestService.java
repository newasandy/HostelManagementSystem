package org.example.service;

import org.example.daoImplementation.LeaveRequestDAOImp;
import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;

import java.util.List;

public class LeaveRequestService {
    private final LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAOImp();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();


    public List<LeaveRequest> viewUserLeaveRequest(Long userId){

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
}
