package org.example.service;

import org.example.daoImplementation.LeaveRequestDAOImp;
import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;
import org.example.model.StatusMessageModel;

import java.util.List;

public class LeaveRequestService {
    private final LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAOImp();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();


    public List<LeaveRequest> viewUserLeaveRequestByUser(Long userId){
        return leaveRequestDAO.getUserLeaveRequestByUserId(userId);
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

    public LeaveRequest getLeaveDetailsByRowNumber(int rowNumber, Long userId){
        List<LeaveRequest> leaveRequestList = leaveRequestDAO.getUserLeaveRequestByUserId(userId);
        if (rowNumber <= 0 || rowNumber > leaveRequestList.size() ){
            System.out.println("Invalid Row Number");
        }
        LeaveRequest lr = leaveRequestDAO.checkLeaveRequest(leaveRequestList.get(rowNumber-1).getStudentId().getId());
        if (lr != null){
            return leaveRequestList.get(rowNumber-1);
        }else {
           return null;
        }
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
