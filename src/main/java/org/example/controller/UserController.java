package org.example.controller;

import org.example.model.Users;

import java.util.Scanner;

public class UserController {
    private final RoomAllocationController roomAllocationController = new RoomAllocationController();
    private final VisitorsController visitorsController = new VisitorsController();
    private final LeaveRequestController leaveRequestController = new LeaveRequestController();

    public void userLoginService(Users loginUser){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1. View Allocated Room");
            System.out.println("2. Visited By");
            System.out.println("3. View Leave Request");
            System.out.println("4. Logout");
            int option = sc.nextInt();
            if (option ==1){
                roomAllocationController.getUserAllocatedDetailsController(loginUser);
            } else if (option == 2) {
                visitorsController.userVisitedBy(loginUser);
            } else if (option == 3) {
                leaveRequestController.viewUserViewRequest(loginUser);
            } else if (option == 4) {
                break;
            }

        }

    }
}
