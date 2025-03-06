package org.example.controller;

import org.example.model.Users;

import java.util.Scanner;

public class UserController {
    private final RoomsController roomsController = new RoomsController();
    private final VisitorsController visitorsController = new VisitorsController();
    private final LeaveRequestController leaveRequestController = new LeaveRequestController();
    private final MonthyFeeController monthyFeeController = new MonthyFeeController();
    public void userLoginService(Users loginUser){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1. View Allocated Room");
            System.out.println("2. Visited By");
            System.out.println("3. View Leave Request");
            System.out.println("4. View Fee Details");
            System.out.println("5. Logout");
            int option = sc.nextInt();
            if (option ==1){
                roomsController.getUserAllocatedDetailsController(loginUser);
            } else if (option == 2) {
                visitorsController.userVisitedBy(loginUser);
            } else if (option == 3) {
                leaveRequestController.viewUserViewRequest(loginUser);
            } else if (option == 4) {
                monthyFeeController.viewFeeByUser(loginUser);
            } else if (option == 5) {
                break;
            }

        }

    }
}
