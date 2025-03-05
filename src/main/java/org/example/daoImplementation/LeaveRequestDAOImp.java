package org.example.daoImplementation;

import org.example.daoInterface.LeaveRequestDAO;
import org.example.model.LeaveRequest;


public class LeaveRequestDAOImp extends BaseDAOImp<LeaveRequest, Long> implements LeaveRequestDAO {
    public LeaveRequestDAOImp(){
        super(LeaveRequest.class);
    }
}
