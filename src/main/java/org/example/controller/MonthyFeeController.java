package org.example.controller;

import org.example.daoImplementation.MonthlyFeeDAOImpl;
import org.example.daoInterface.MonthlyFeeDAO;
import org.example.model.MonthlyFee;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.MonthlyFeeService;

import java.sql.Timestamp;
import java.util.List;

public class MonthyFeeController {
    private MonthlyFeeDAO monthlyFeeDAO = new MonthlyFeeDAOImpl();
    private MonthlyFeeService monthlyFeeService = new MonthlyFeeService(monthlyFeeDAO);


    public StatusMessageModel assignMonthlyFee(Users selectStudent, String months, int years, double amount, Timestamp issueDate){
        MonthlyFee monthlyFee = new MonthlyFee();
        monthlyFee.setStudentId(selectStudent);
        monthlyFee.setMonth(months);
        monthlyFee.setYear(years);
        monthlyFee.setFeeAmount(amount);
        monthlyFee.setIssueDate(issueDate);
        monthlyFee.setPaid(0.0);
        monthlyFee.setDue(amount);
        return monthlyFeeService.setStudentMonthlyFee(monthlyFee);

    }

    public void viewAllStudentFee(){
        List<MonthlyFee> allFeeDetails = monthlyFeeService.getAllStudentFeeDetails();
        int sn =1;
        System.out.println("SN \t\t Student Name\t\t\t Issue Date\t\t\t Months \t\t\t Fee Amount\t\t\t Paid \t\t\t Due");
        for (MonthlyFee feeDetails : allFeeDetails){
            System.out.println(sn+"\t\t"+feeDetails.getStudentId().getFullName()+"\t\t\t"+feeDetails.getIssueDate()+"\t\t\t"+feeDetails.getMonth()+"\t\t\t"+feeDetails.getFeeAmount()+"\t\t\t"+feeDetails.getPaid()+"\t\t\t"+feeDetails.getDue());
            sn++;
        }
    }

    public void viewFeeByUser(Users user){
        List<MonthlyFee> allFeeDetails = monthlyFeeService.getUserAllFeeDetails(user.getId());
        int sn =1;
        System.out.println("SN \t\t Student Name\t\t\t Issue Date\t\t\t Fee Amount\t\t\t Paid \t\t\t Due");
        for (MonthlyFee feeDetails : allFeeDetails){
            System.out.println(sn+"\t\t"+feeDetails.getStudentId().getFullName()+"\t\t\t"+feeDetails.getIssueDate()+"\t\t\t"+feeDetails.getFeeAmount()+"\t\t\t"+feeDetails.getPaid()+"\t\t\t"+feeDetails.getDue());
            sn++;
        }
    }

    public void viewALlUnpaidFee(){
        List<MonthlyFee> allUnPaidFeeDetails = monthlyFeeService.getAllUserUnpaidFeeDetails();
        int sn =1;
        System.out.println("SN \t\t Student Name\t\t\t Issue Date\t\t\t Fee Amount\t\t\t Paid \t\t\t Due");
        for (MonthlyFee feeDetails : allUnPaidFeeDetails){
            System.out.println(sn+"\t\t"+feeDetails.getStudentId().getFullName()+"\t\t\t"+feeDetails.getIssueDate()+"\t\t\t"+feeDetails.getFeeAmount()+"\t\t\t"+feeDetails.getPaid()+"\t\t\t"+feeDetails.getDue());
            sn++;
        }
    }

    public MonthlyFee getUnpaidFeeDetailsByRow(int rowNUmber){
        List<MonthlyFee> allUnPaidFeeDetails = monthlyFeeService.getAllUserUnpaidFeeDetails();
        if (rowNUmber <1 || rowNUmber > allUnPaidFeeDetails.size()){
            System.out.println("Invalid Row Number");
            return null;
        }
        return allUnPaidFeeDetails.get(rowNUmber-1);
    }

    public StatusMessageModel payMonthlyFee(MonthlyFee unpaidMonthlyFee , double amount){
        return monthlyFeeService.feePaid(unpaidMonthlyFee , amount);
    }
}
