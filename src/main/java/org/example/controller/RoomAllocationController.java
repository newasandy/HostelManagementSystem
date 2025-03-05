package org.example.controller;

import org.example.model.*;
import org.example.service.AdminService;
import org.example.service.RoomAllocationService;
import org.example.service.RoomsService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RoomAllocationController {
    private final RoomAllocationService roomAllocationService = new RoomAllocationService();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomsService roomsService = new RoomsService();
    private final Scanner sc = new Scanner(System.in);
    private RoomAllocation roomAllocation = new RoomAllocation();
    private AdminController adminController = new AdminController();

    public void getUserAllocatedDetailsController(Users users){
        List<RoomAllocation> allocatedDetails = roomAllocationService.getAllocatedDetails(users.getId());
        for (RoomAllocation allocation : allocatedDetails){
            System.out.println(allocation.getRoomId().getRoomNumber() + " \t\t"+allocation.getAllocationDate() +"\t\t\t" + allocation.getUnallocationDate());
        }
    }

    public void viewAllocatedDetails(){
        roomAllocationService.getAllocationDetails();
        while (true){
            sc.nextLine();
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
        roomAllocationService.viewUnallocatedStudent();
        System.out.println("Select student by row number ");
        int studentRowNumber = sc.nextInt();
        Users studentId = roomAllocationService.getUnallocatedUserIdByRowNumber(studentRowNumber);
        int roomRowNumber;
        Rooms roomId ;
        while (true){
            roomsService.getAllRoom();
            System.out.println("Select Room by row Number ");
            roomRowNumber = sc.nextInt();
            int roomCapacity = roomAllocationService.getRoomCapacity(roomRowNumber);
            roomId = roomAllocationService.getRoomIdByRowNumber(roomRowNumber);
            if (roomAllocationService.isRoomAvailable(roomId,roomCapacity)){
                break;
            }
            System.out.println("Selected Room Is full select other room or enter exit:");
            sc.nextLine();
            String inputs = sc.nextLine();
            if (inputs.equals( "exit")){
                adminController.loginedAdminService();
            }
        }
        Date getDate = new Date();
        Timestamp allocationDate = new Timestamp(getDate.getTime());

        roomAllocation.setStudentId(studentId);
        roomAllocation.setRoomId(roomId);
        roomAllocation.setAllocationDate(allocationDate);

        statusMessageModel = roomAllocationService.setStudentAtRoom(roomAllocation);
        System.out.println(statusMessageModel.getMessage());
    }

    public void unallocatedRoom(){
        roomAllocationService.getAllRoomAllocatedList();
        System.out.println("Enter row number that want to unallocated");
        System.out.println("For exit enter 0");
        int rowNumber = sc.nextInt();
        if (rowNumber > 0){
            statusMessageModel = roomAllocationService.unallocatedStudentFromRoom(rowNumber);
            System.out.println(statusMessageModel.getMessage());
        }
    }


}
