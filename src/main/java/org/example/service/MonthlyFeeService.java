package org.example.service;

import org.example.daoInterface.MonthlyFeeDAO;
import org.example.model.MonthlyFee;
import org.example.model.StatusMessageModel;

import java.util.List;

public class MonthlyFeeService {

    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private MonthlyFeeDAO monthlyFeeDAO ;

    public MonthlyFeeService(MonthlyFeeDAO monthlyFeeDAO){
        this.monthlyFeeDAO = monthlyFeeDAO;
    }

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

    public List<MonthlyFee> getAllUserUnpaidFeeDetails(){
        return monthlyFeeDAO.getAllUserUnPaidFee();
    }
    public StatusMessageModel feePaid(MonthlyFee payFee , double amount){
        double paidAmount = payFee.getPaid()+amount;
        double dueAmount = payFee.getDue() - amount;
        payFee.setPaid(paidAmount);
        payFee.setDue(dueAmount);
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
