package org.example.view;

import org.example.controller.RoomsController;
import org.example.controller.UsersController;
import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;
import org.example.model.Users;

import java.util.Scanner;

public class RoomsView {
    private Scanner sc = new Scanner(System.in);
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomsController roomsController = new RoomsController();
    private UsersController usersController = new UsersController();

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
                disableRooms();
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
        statusMessageModel = roomsController.addNewRoom(roomNumber,roomCapacity);
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

    public void disableRooms(){
        roomsController.getAllRooms();
        System.out.println("Pick room by Row Number which want to delete:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        Rooms selectRoom = roomsController.getRoomByRowNumber(rowNumber);
        if (roomsController.unallocatedBeforeDisable(selectRoom.getId())){
            selectRoom.setStatus(false);
            statusMessageModel = roomsController.deleteRoomController(selectRoom);
        }else {
            System.out.println("Room unable to disable Room before unallocated student");
        }
        System.out.println(statusMessageModel.getMessage());
    }


    public void viewAllocatedDetails(){
        roomsController.viewAllAllocatedDetails();
        while (true){
            System.out.println("1. Allocated New Student in Room");
            System.out.println("2. Unallocated Student From Room");
            System.out.println("3. Exit");
            int option = sc.nextInt();
            if (option ==1){
                roomAllocation();
            } else if (option == 2) {
                roomUnallocation();
            } else if (option == 3) {
                break;
            }
        }
    }

    public void roomAllocation(){
        Users selectStudent = new Users();
        Rooms selectRoom = new Rooms();
        if (usersController.viewUnallocatedStudent()){
            System.out.println("Select student by row number ");
            int studentRowNumber = sc.nextInt();
            selectStudent = usersController.getUnallocatedStudentByRow(studentRowNumber);
        }else {
            System.out.println("All Student Are Allocated");
        }
        if (roomsController.viewAvailableRoom()){
            System.out.println("Select Room by row Number ");
            int roomRowNumber = sc.nextInt();
            selectRoom = roomsController.getAvailableRoomByRow(roomRowNumber);
        }else {
            System.out.println("Room Is Not Available");
        }
        statusMessageModel = roomsController.allocatedRoom(selectStudent,selectRoom);
        System.out.println(statusMessageModel.getMessage());
    }

    public void roomUnallocation(){
        if (roomsController.viewOnlyAllocatedDetails()){
            System.out.println("Enter row number that want to unallocated");
            int rowNumber =sc.nextInt();
            RoomAllocation selectDetails = roomsController.getOnlyAllocatedByRow(rowNumber);
            if (selectDetails != null){
                statusMessageModel = roomsController.unallocatedRoom(selectDetails);
                System.out.println(statusMessageModel.getMessage());
            }
        }else {
            System.out.println("No One is Allocated");
        }
    }
}
