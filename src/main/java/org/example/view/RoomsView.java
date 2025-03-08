package org.example.view;

import org.example.controller.RoomsController;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;

import java.util.Scanner;

public class RoomsView {
    private Scanner sc = new Scanner(System.in);
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomsController roomsController = new RoomsController();

    public void viewAllRooms(){
        roomsController.getAllRooms();
        while (true){
            System.out.println("1. Add New Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. Exit");
            System.out.println("=================================");
            int option = sc.nextInt();
            if (option == 1){
                addNewRooms();
            } else if (option == 2) {
                updateRooms();
            } else if (option == 3) {
//                deleteRoom();
            } else if (option == 4) {
                break;
            }
        }
    }

    public void addNewRooms(){
        sc.nextLine();
        System.out.println("Enter New Room Number");
        int roomNumber = sc.nextInt();
        System.out.println("Enter Room Capacity");
        int roomCapacity = sc.nextInt();
        statusMessageModel = roomsController.addNewRoomController(roomNumber,roomCapacity);
        System.out.println(statusMessageModel.getMessage());
    }

    public void updateRooms(){
        roomsController.getAllRooms();
        System.out.println("Pick room by Row Number which want to update:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        Rooms rooms = roomsController.getRoomByRowNumber(rowNumber);
        while (true){
            System.out.println("Which field want to update:");
            System.out.println("===============================");
            System.out.println("1. Room Number: "+rooms.getRoomNumber());
            System.out.println("2. Room Capacity: "+rooms.getCapacity());
            System.out.println("3. Update");
            int option = sc.nextInt();
            if (option ==1){
                sc.nextLine();
                System.out.println("Enter new Room Number:");
                rooms.setRoomNumber(sc.nextInt());
            } else if (option ==2) {
                sc.nextLine();
                System.out.println("Enter new Room Capacity:");
                rooms.setCapacity(sc.nextInt());
            }else if (option ==3) {
                break;
            }
        }

        statusMessageModel = roomsController.updateRoom(rooms);
        System.out.println(statusMessageModel.getMessage());
    }
}
