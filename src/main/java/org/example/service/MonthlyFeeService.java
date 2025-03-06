package org.example.service;

import org.example.daoImplementation.MonthlyFeeDAOImpl;
import org.example.daoInterface.MonthlyFeeDAO;
import org.example.model.MonthlyFee;
import org.example.model.StatusMessageModel;

import java.util.List;

public class MonthlyFeeService {

    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private MonthlyFeeDAO monthlyFeeDAO = new MonthlyFeeDAOImpl();

    public StatusMessageModel setStudentMonthlyFee(MonthlyFee assignFee){
        if (monthlyFeeDAO.add(assignFee)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Fee Assign Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Assign Fee");
        }
        return statusMessageModel;
    }


    public List<MonthlyFee> getAllStudentFeeDetails(){
        return monthlyFeeDAO.getAll();
    }

    public List<MonthlyFee> getUserAllFeeDetails(Long userId){
        return monthlyFeeDAO.getUserFeeDetails(userId);
    }

    public List<MonthlyFee> getUserUnpaidFee(Long userId){
        return monthlyFeeDAO.getUserUnPaidFee(userId);
    }

    public StatusMessageModel feePaidByUser(MonthlyFee payFee){
        if (monthlyFeeDAO.update(payFee)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Paid Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Failed to Paid");
        }
        return statusMessageModel;
    }


}
