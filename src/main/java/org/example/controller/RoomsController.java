package org.example.controller;

import jdk.jshell.Snippet;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;
import org.example.service.RoomsService;

import java.util.Scanner;

public class RoomsController {
    private RoomsService roomsService = new RoomsService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    Scanner sc = new Scanner(System.in);


    public void viewAllRoom(){
        roomsService.getAllRoom();
        while (true){
            System.out.println("1. Add New Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. Exit");
            System.out.println("=================================");
            int option = sc.nextInt();
            if (option == 1){
                addNewRoom();
            } else if (option == 2) {
                updateRoom();
            } else if (option == 3) {
                deleteRoom();
            } else if (option == 4) {
                break;
            }
        }
    }

    public void addNewRoom(){
        sc.nextLine();
        Rooms roomsModel = new Rooms();

        System.out.println("Enter New Room Number");
        int roomNumber = sc.nextInt();
        System.out.println("Enter Room Capacity");
        int roomCapacity = sc.nextInt();

        roomsModel.setRoomNumber(roomNumber);
        roomsModel.setCapacity(roomCapacity);

        statusMessageModel = roomsService.addNewRoomService(roomsModel);
        System.out.println(statusMessageModel.getMessage());

    }

    public void updateRoom(){
        roomsService.getAllRoom();
        System.out.println("Pick room by Row Number which want to update:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        Rooms rooms = roomsService.getRoomByRowNumber(rowNumber);
        while (true){
            System.out.println("Which field want to update:");
            System.out.println("===============================");
            System.out.println("1. Room Number: "+rooms.getRoomNumber());
            System.out.println("2. Room Capacity: "+rooms.getCapacity());
            System.out.println("3. Exit");
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
        statusMessageModel = roomsService.updateRoomService(rooms);
        System.out.println(statusMessageModel.getMessage());
    }

    public void deleteRoom(){
        roomsService.getAllRoom();
        System.out.println("Pick room by Row Number which want to delete:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        statusMessageModel = roomsService.deleteRoomService(rowNumber);
        System.out.println(statusMessageModel.getMessage());
    }
}
