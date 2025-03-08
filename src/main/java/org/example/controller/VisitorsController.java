package org.example.controller;

import org.example.model.Address;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.model.Visitors;
import org.example.service.AdminService;
import org.example.service.VisitorService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VisitorsController {
    private final VisitorService visitorService = new VisitorService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final AdminService adminService = new AdminService();
    private final Scanner sc = new Scanner(System.in);

    public void getUserVisitedBy(Users users){
        List<Visitors> visitors = visitorService.userVisitedBy(users.getId());
        for (Visitors visitor : visitors){
            System.out.println(visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
        }
    }

    public void viewVisitors(){
        visitorService.getAllVisitor();
        while (true){
            System.out.println("1. Add Visitor");
            System.out.println("2. Update Visitor when Exit");
            System.out.println("3. Exit");
            int option = sc.nextInt();
            if (option == 1){
                System.out.println("Add Visitor");
                System.out.println("===================================");
                addVisitor();
            } else if (option == 2) {
                updateVisitor();
            } else if (option == 3) {
                break;
            }
        }
    }

    public void addVisitor(){
        Visitors visitor = new Visitors();
        adminService.getAllUserAndAddress();
        System.out.println("Select Student By Row Number");
        int rowNumber = sc.nextInt();
        Address user = adminService.getUserDetailByRowNumber(rowNumber);
        sc.nextLine();
        System.out.println("Enter Visitor Name");
        String visitorName = sc.nextLine();
        System.out.println("Enter Visitor Relation With Student");
        String relation = sc.nextLine();
        System.out.println("Enter Reason");
        String reason = sc.nextLine();
        Date getEntryDate = new Date();
        Timestamp entryDateTime = new Timestamp(getEntryDate.getTime());

        visitor.setStudentId(user.getUser());
        visitor.setFullName(visitorName);
        visitor.setRelation(relation);
        visitor.setReason(reason);
        visitor.setEntryDatetime(entryDateTime);

        statusMessageModel = visitorService.addVisitorService(visitor);
        System.out.println(statusMessageModel.getMessage());
    }

    public void updateVisitor(){
        visitorService.getAllVisitor();
        System.out.println("========================================");
        System.out.println("Select the visitor who exit");
        int rowNumber =sc.nextInt();
        statusMessageModel = visitorService.exitVisitorUpdate(rowNumber);
        System.out.println(statusMessageModel.getMessage());
    }
}
