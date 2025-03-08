package org.example.controller;

import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.RoomsService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RoomsController {
    private RoomsService roomsService = new RoomsService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    Scanner sc = new Scanner(System.in);

    public void viewAllAllocatedDetails(){
        List<RoomAllocation> roomAllocatedList = roomsService.getAllocationDetails();
        System.out.println("SN\t\t Student Name \t\t\t Room Number \t\t\t Allocated Date \t\t\t Unallocated Date");
        int rowNumber =1;
        for (RoomAllocation roomAllocation : roomAllocatedList){
            System.out.println(rowNumber+"\t\t"+roomAllocation.getStudentId().getFullName()+"\t\t\t"+roomAllocation.getRoomId().getRoomNumber()+"\t\t\t"+roomAllocation.getAllocationDate()+"\t\t\t"+roomAllocation.getUnallocationDate());
            rowNumber++;
        }
    }

    public void viewAllRoom(){
        List<Rooms> roomList =roomsService.getAllRoom();
        int sn =1;
        for (Rooms room : roomList){
            System.out.println(sn+"\t\t"+room.getRoomNumber()+"\t\t"+room.getCapacity());
            sn++;
        }
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

        roomsModel.setStatus(true);
        roomsModel.setRoomNumber(roomNumber);
        roomsModel.setCapacity(roomCapacity);

        statusMessageModel = roomsService.addNewRoomService(roomsModel);
        System.out.println(statusMessageModel.getMessage());

    }

    public void updateRoom(){
        List<Rooms> roomList =roomsService.getAllRoom();
        int sn =1;
        for (Rooms room : roomList){
            System.out.println(sn+"\t\t"+room.getRoomNumber()+"\t\t"+room.getCapacity());
            sn++;
        }
        System.out.println("Pick room by Row Number which want to update:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        Rooms rooms = roomsService.getRoomByRowNumber(rowNumber);
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
        statusMessageModel = roomsService.updateRoomService(rooms);
        System.out.println(statusMessageModel.getMessage());
    }

    public void deleteRoom(){
        List<Rooms> roomList =roomsService.getAllRoom();
        int sn =1;
        for (Rooms room : roomList){
            System.out.println(sn+"\t\t"+room.getRoomNumber()+"\t\t"+room.getCapacity());
            sn++;
        }
        System.out.println("Pick room by Row Number which want to delete:");
        System.out.println("===========================================");
        int rowNumber = sc.nextInt();
        if (rowNumber <1 || rowNumber > roomList.size()){
            System.out.println("Invalid Row Number");
        }else {
            boolean unallocatedStudent = unallocatedBeforeDisable(roomList.get(rowNumber-1).getId());
            if (unallocatedStudent){
                statusMessageModel = roomsService.deleteRoomService(roomList.get(rowNumber-1));
                System.out.println(statusMessageModel.getMessage());
            }else {
                System.out.println("!!! Before Disable Room Unallocated Student Not Success");
            }
        }
    }

    public void getUserAllocatedDetailsController(Users users){
        List<RoomAllocation> allocatedDetails = roomsService.getAllocatedDetails(users.getId());
        for (RoomAllocation allocation : allocatedDetails){
            System.out.println(allocation.getRoomId().getRoomNumber() + " \t\t"+allocation.getAllocationDate() +"\t\t\t" + allocation.getUnallocationDate());
        }
    }

    public void viewAllocatedDetails(){
        viewAllAllocatedDetails();
        while (true){
            System.out.println("1. Allocated New Student in Room");
            System.out.println("2. Unallocated Student From Room");
            System.out.println("3. Exit");
            int option = sc.nextInt();
            if (option ==1){
                allocatedRoom();
            } else if (option == 2) {
                unallocatedRoom();
            } else if (option == 3) {
                break;
            }
        }
    }

    public void allocatedRoom(){
        RoomAllocation allocationStudent = new RoomAllocation();
        List<Users> unallocatedUsers = roomsService.getUnallocatedStudent();
        for (Users user : unallocatedUsers){
            System.out.println(user.getId()+"\t\t"+user.getFullName()+"\t\t"+user.getEmail()+"\t\t"+user.getRoles());
        }
        System.out.println("===================================");
        Users selectUser ;
        int studentRowNumber ;
        while (true){
            System.out.println("Select student by row number ");
            studentRowNumber = sc.nextInt();
            if (studentRowNumber <1 || studentRowNumber > unallocatedUsers.size()){
                System.out.println("Invalid Row number");
            }else {
                selectUser = unallocatedUsers.get(studentRowNumber-1);
                break;
            }
        }
        List<Rooms> roomList = roomsService.getAvailableRoom();
        if (roomList.isEmpty()){
            System.out.println("Room Is Not Available");
        }else {
            System.out.println("SN \t Room ID \t Room number \t Room Capacity");
            System.out.println("=============================================");
            int rowNumber = 1;
            for (Rooms room : roomList){
                System.out.println(rowNumber + ".  "+ room.getId()+"\t"+room.getRoomNumber()+"\t"+room.getCapacity());
                rowNumber++;
            }
            int roomRowNumber;
            Rooms selectRoom ;
            while (true){
                System.out.println("Select Room by row Number ");
                System.out.println("Enter 0 for exit");
                roomRowNumber = sc.nextInt();
                if (roomRowNumber <1 || roomRowNumber > roomList.size()){
                    System.out.println("Invalid Room Select row number");
                }  else {
                    selectRoom = roomList.get(roomRowNumber-1);
                    break;
                }
            }
            Date getDate = new Date();
            Timestamp allocationDate = new Timestamp(getDate.getTime());
            allocationStudent.setStudentId(selectUser);
            allocationStudent.setRoomId(selectRoom);
            allocationStudent.setAllocationDate(allocationDate);
            statusMessageModel = roomsService.setStudentAtRoom(allocationStudent);
            System.out.println(statusMessageModel.getMessage());

        }
    }

    public void unallocatedRoom(){
        List<RoomAllocation> roomAllocationsList = roomsService.getAllRoomAllocatedList();
        System.out.println("SN \t S ID \t R ID \t allocated \t unallocated");
        System.out.println("=============================================");
        int rowNumber =1 ;
        for (RoomAllocation list : roomAllocationsList){
            System.out.println(rowNumber +". "+list.getStudentId().getId()+"\t"+list.getRoomId().getId()+"\t"+list.getAllocationDate()+"\t"+list.getUnallocationDate());
            rowNumber++;
        }
        RoomAllocation selectUnallocatedRow;
        int selectRowNumber ;
        while (true){
            System.out.println("Enter row number that want to unallocated");
            selectRowNumber = sc.nextInt();
            if (selectRowNumber <1 || selectRowNumber > roomAllocationsList.size()){
                System.out.println("Invalid Selected Row Number");
            }else {
                selectUnallocatedRow = roomAllocationsList.get(selectRowNumber-1);
                break;
            }
        }
        Date date = new Date();
        Timestamp unallocationDate = new Timestamp(date.getTime());
        selectUnallocatedRow.setUnallocationDate(unallocationDate);
        statusMessageModel = roomsService.unallocatedStudentFromRoom(selectUnallocatedRow);
        System.out.println(statusMessageModel.getMessage());
    }

    public boolean unallocatedBeforeDisable(Long roomId){
        return roomsService.disableRoomUnallocationServic(roomId);
    }
}
