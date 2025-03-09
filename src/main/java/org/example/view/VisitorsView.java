package org.example.view;

import org.example.controller.UsersController;
import org.example.controller.VisitorsController;
import org.example.model.StatusMessageModel;

import java.util.Scanner;

public class VisitorsView {
    private Scanner sc = new Scanner(System.in);
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private VisitorsController visitorsController = new VisitorsController();
    private UsersController usersController = new UsersController();

    public void viewVisitors(){
        visitorsController.viewAllVisitor();
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
        usersController.viewOnlyStudent();
        System.out.println("Select Student By Row Number");
        int rowNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Visitor Name");
        String visitorName = sc.nextLine();
        System.out.println("Enter Visitor Relation With Student");
        String relation = sc.nextLine();
        System.out.println("Enter Reason");
        String reason = sc.nextLine();
        statusMessageModel = visitorsController.addVisitor(rowNumber,visitorName,relation,reason);
    }

    public void updateVisitor(){
        visitorsController.viewAllNotExitVisitor();
        System.out.println("========================================");
        System.out.println("Select the visitor who exit");
        int rowNumber =sc.nextInt();
        statusMessageModel = visitorsController.updateVisitor(rowNumber);
        System.out.println(statusMessageModel.getMessage());
    }
}
