package org.example.controller;

import org.example.model.MonthlyFee;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.AdminService;
import org.example.service.MonthlyFeeService;
import org.example.utils.PasswordUtil;

import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MonthyFeeController {
    private AdminService adminService = new AdminService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private MonthlyFeeService monthlyFeeService = new MonthlyFeeService();
    private Scanner sc = new Scanner(System.in);

    public void assignMonthlyFee(){
        List<Users> allUser = adminService.viewOnlyStudent();
        System.out.printf("%-15s %-20s %-25s%n", "User Id", "Full Name", "Email");
        System.out.println("======================================================");
        for(Users student : allUser ){
            System.out.printf("%-15s %-20s %-25s%n",student.getId(),student.getFullName(),student.getEmail());
        }
        System.out.println("============================");
        System.out.println("select student by row number:");
        int rowNumber = sc.nextInt();
        if (rowNumber < 1 || rowNumber > allUser.size()){
            System.out.println("Invalid row Number");
        }else {
            MonthlyFee assignMonthlyFee = new MonthlyFee();
            System.out.println("Select Months:");
            System.out.println("=========================");
            String[] monthsName = {"January","February","March","April", "May","June","July","August","September","October","November","December"};
            for (int i = 1 ; i <= monthsName.length ; i++){
                System.out.println(i + ". "+monthsName[i]);
            }
            String months = "";
            int monthsNumber = sc.nextInt();

            if (monthsNumber > 0 && monthsNumber <= monthsName.length){
                months = monthsName[monthsNumber-1];
            }
            int currentYear = Year.now().getValue();
            System.out.println("enter amount:");
            double amount = sc.nextDouble();
            Date date = new Date();
            Timestamp issueDate = new Timestamp(date.getTime());
            Users user = allUser.get(rowNumber-1);
            double paid = 0.0;

            assignMonthlyFee.setStudentId(user);
            assignMonthlyFee.setYear(currentYear);
            assignMonthlyFee.setFeeAmount(amount);
            assignMonthlyFee.setDue(amount);
            assignMonthlyFee.setMonth(months);
            assignMonthlyFee.setIssueDate(issueDate);
            assignMonthlyFee.setPaid(paid);

            statusMessageModel = monthlyFeeService.setStudentMonthlyFee(assignMonthlyFee);
            System.out.println(statusMessageModel.getMessage());

        }
    }

    public void viewAllStudentFee(){
        List<MonthlyFee> allFeeDetails = monthlyFeeService.getAllStudentFeeDetails();
        int sn =1;
        System.out.println("SN \t\t Student Name\t\t\t Issue Date\t\t\t Fee Amount\t\t\t Paid \t\t\t Due");
        for (MonthlyFee feeDetails : allFeeDetails){
            System.out.println(sn+"\t\t"+feeDetails.getStudentId().getFullName()+"\t\t\t"+feeDetails.getIssueDate()+"\t\t\t"+feeDetails.getFeeAmount()+"\t\t\t"+feeDetails.getPaid()+"\t\t\t"+feeDetails.getDue());
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
        System.out.println("===========================");
        System.out.println("1. Paid Due Fee");
        System.out.println("2. Exit");
        System.out.println("+++++");
        int option = sc.nextInt();
        if (option == 1){
            payFee(user);
        }

    }

    public void payFee(Users user){
        List<MonthlyFee> allUnPaidFeeDetails = new ArrayList<>() ;
        if (user.getRoles().equals("USER")){
            allUnPaidFeeDetails = monthlyFeeService.getUserUnpaidFee(user.getId());
        }else if (user.getRoles().equals("ADMIN")){
            allUnPaidFeeDetails = monthlyFeeService.getAllUserUnpaidFeeDetails();
        }
        int sn =1;
        System.out.println("SN \t\t Student Name\t\t\t Issue Date\t\t\t Fee Amount\t\t\t Paid \t\t\t Due");
        for (MonthlyFee feeDetails : allUnPaidFeeDetails){
            System.out.println(sn+"\t\t"+feeDetails.getStudentId().getFullName()+"\t\t\t"+feeDetails.getIssueDate()+"\t\t\t"+feeDetails.getFeeAmount()+"\t\t\t"+feeDetails.getPaid()+"\t\t\t"+feeDetails.getDue());
            sn++;
        }
        System.out.println("===================");
        System.out.println("Select Month by Row Number for Pay Fee:");
        MonthlyFee forPay;
        int rowNumber = sc.nextInt();
        if (rowNumber <1 || rowNumber > allUnPaidFeeDetails.size()){
             System.out.println("Invalid Row Number");
         }else {
             forPay = allUnPaidFeeDetails.get(rowNumber-1);
             System.out.println("1. Full Pay");
             System.out.println("2. Only certain Amount");
             int option = sc.nextInt();
             if (option == 1){
                 System.out.println("+++++");
                 System.out.println("Your Due Amount: "+forPay.getDue());
                 System.out.println("+++++");
                 double amount = forPay.getDue();
                 double paidAmount = amount + forPay.getPaid();
                 double dueAmount = forPay.getDue() - amount;
                 sc.nextLine();
                 System.out.println("Enter User Password:");
                 String conformPassword = sc.nextLine();
                 if (PasswordUtil.verifyPassword(conformPassword,user.getPasswords())){
                     forPay.setPaid(paidAmount);
                     forPay.setDue(dueAmount);
                     statusMessageModel = monthlyFeeService.feePaidByUser(forPay);
                     System.out.println(statusMessageModel.getMessage());
                 }else {
                     System.out.println("!! Password Incorrect");
                 }
             } else if (option == 2) {
                 System.out.println("+++++");
                 System.out.println("Your Due Amount: "+forPay.getDue());
                 System.out.println("+++++");
                 System.out.println("Enter Amount to Pay:");
                 double payAmount = sc.nextDouble();
                 if (payAmount <= forPay.getDue()){
                     double paidAmount = payAmount + forPay.getPaid();
                     double dueAmount = forPay.getDue() - payAmount;
                     sc.nextLine();
                     System.out.println("Enter User Password:");
                     String conformPassword = sc.nextLine();
                     if (PasswordUtil.verifyPassword(conformPassword,user.getPasswords())){
                         forPay.setPaid(paidAmount);
                         forPay.setDue(dueAmount);
                         statusMessageModel = monthlyFeeService.feePaidByUser(forPay);
                         System.out.println(statusMessageModel.getMessage());
                     }else {
                         System.out.println("!! Password Incorrect");
                     }
                 }else {
                     System.out.println("!! Given Amount is Greater then Due Amount");
                 }
             }

         }
     }
}
