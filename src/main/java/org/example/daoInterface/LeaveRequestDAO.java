package org.example.daoInterface;

import org.example.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestDAO extends BaseDAO<LeaveRequest, Long>{

    List<LeaveRequest> getUserLeaveRequestByUserId(Long userId);
    LeaveRequest checkLeaveRequest(Long userId);
    List<LeaveRequest> getAllPendingRequest();
}
