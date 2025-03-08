package org.example.controller;

import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.RoomDAO;
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

    private RoomDAO roomDAO = new RoomDAOImp();

    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    Scanner sc = new Scanner(System.in);

    public void getAllRooms(){
        List<Rooms> roomList =roomsService.getAllRoom();
        System.out.printf("%-5s %-10s %-10s%n","SN","Room No.","Capacity");
        int sn =1;
        for (Rooms room : roomList){
            System.out.printf("%-5s %-10s %-10s%n",sn, room.getRoomNumber(),room.getCapacity());
            sn++;
        }
    }

    public Rooms getRoomByRowNumber(int rowNumber){
        List<Rooms> rooms = roomDAO.getAll();
        if (rowNumber <0 || rowNumber > rooms.size()){
            System.out.println("invalid Row Number");
        }
        return rooms.get(rowNumber - 1);
    }

    public void viewAllAllocatedDetails(){
        List<RoomAllocation> roomAllocatedList = roomAllocationDAO.getAll();
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

    }

    public StatusMessageModel addNewRoomController(int roomNumber, int roomCapacity){
        Rooms roomsModel = new Rooms();
        roomsModel.setStatus(true);
        roomsModel.setRoomNumber(roomNumber);
        roomsModel.setCapacity(roomCapacity);
        return roomsService.addNewRoomService(roomsModel);

    }

    public StatusMessageModel updateRoom(Rooms room){
        return roomsService.updateRoomService(room);
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
