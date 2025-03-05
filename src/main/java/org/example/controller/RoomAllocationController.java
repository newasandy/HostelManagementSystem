package org.example.controller;

import org.example.model.*;
import org.example.service.RoomAllocationService;
import org.example.service.RoomsService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RoomAllocationController {
    private final RoomAllocationService roomAllocationService = new RoomAllocationService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final RoomsService roomsService = new RoomsService();
    private final Scanner sc = new Scanner(System.in);

    public void getUserAllocatedDetailsController(Users users){
        List<RoomAllocation> allocatedDetails = roomAllocationService.getAllocatedDetails(users.getId());
        for (RoomAllocation allocation : allocatedDetails){
            System.out.println(allocation.getRoomId().getRoomNumber() + " \t\t"+allocation.getAllocationDate() +"\t\t\t" + allocation.getUnallocationDate());
        }
    }

    public void viewAllocatedDetails(){
        roomAllocationService.getAllocationDetails();
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
        List<Users> unallocatedUsers = roomAllocationService.getUnallocatedStudent();
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
            roomRowNumber = sc.nextInt();
            if (roomRowNumber <1 || roomRowNumber > roomList.size()){
                System.out.println("Invalid Room Select row number");
            }else {
                selectRoom = roomList.get(roomRowNumber-1);
                break;
            }
        }
        Date getDate = new Date();
        Timestamp allocationDate = new Timestamp(getDate.getTime());
        allocationStudent.setStudentId(selectUser);
        allocationStudent.setRoomId(selectRoom);
        allocationStudent.setAllocationDate(allocationDate);
        statusMessageModel = roomAllocationService.setStudentAtRoom(allocationStudent);
        System.out.println(statusMessageModel.getMessage());

    }

    public void unallocatedRoom(){
        List<RoomAllocation> roomAllocationsList = roomAllocationService.getAllRoomAllocatedList();
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
        statusMessageModel = roomAllocationService.unallocatedStudentFromRoom(selectUnallocatedRow);
        System.out.println(statusMessageModel.getMessage());
    }


}
