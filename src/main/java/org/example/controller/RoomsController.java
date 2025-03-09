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

public class RoomsController {

    private RoomDAO roomDAO = new RoomDAOImp();
    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    private RoomsService roomsService = new RoomsService(roomDAO,roomAllocationDAO);

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
        List<Rooms> rooms = roomsService.getAllRoom();
        if (rowNumber <0 || rowNumber > rooms.size()){
            System.out.println("invalid Row Number");
        }
        return rooms.get(rowNumber - 1);
    }

    public boolean viewAvailableRoom(){
        List<Rooms> roomList = roomsService.getAvailableRoom();
        if (roomList.isEmpty()){
            return false;
        }else {
            int rowNumber = 1;
            for (Rooms room : roomList){
                System.out.println(rowNumber + ".  "+ room.getId()+"\t"+room.getRoomNumber()+"\t"+room.getCapacity());
                rowNumber++;
            }
            return true;
        }
    }

    public Rooms getAvailableRoomByRow(int rowNumber){
        List<Rooms> roomList = roomsService.getAvailableRoom();
        if (rowNumber <1 || rowNumber > roomList.size()){
            System.out.println("Invalid Row Number");
        }
        return roomList.get(rowNumber-1);
    }


    public void viewAllAllocatedDetails(){
        List<RoomAllocation> roomAllocatedList = roomsService.getAllRoomAllocatedDetails();
        System.out.println("SN\t\t Student Name \t\t\t Room Number \t\t\t Allocated Date \t\t\t Unallocated Date");
        int sn =1;
        for (RoomAllocation roomAllocation : roomAllocatedList){
            System.out.println(sn +"\t\t"+roomAllocation.getStudentId().getFullName()+"\t\t\t"+roomAllocation.getRoomId().getRoomNumber()+"\t\t\t"+roomAllocation.getAllocationDate()+"\t\t\t"+roomAllocation.getUnallocationDate());
            sn++;
        }
    }

    public boolean viewOnlyAllocatedDetails(){
        List<RoomAllocation> onlyAllocated = roomsService.getOnlyAllocatedDetails();
        if (onlyAllocated.isEmpty()){
            return false;
        }else {
            System.out.println("SN\t\t Student Name \t\t\t Room Number \t\t\t Allocated Date \t\t\t Unallocated Date");
            int sn =1;
            for (RoomAllocation roomAllocation : onlyAllocated){
                System.out.println(sn +"\t\t"+roomAllocation.getStudentId().getFullName()+"\t\t\t"+roomAllocation.getRoomId().getRoomNumber()+"\t\t\t"+roomAllocation.getAllocationDate()+"\t\t\t"+roomAllocation.getUnallocationDate());
                sn++;
            }
            return true;
        }
    }

    public RoomAllocation getOnlyAllocatedByRow(int rowNumber){
        List<RoomAllocation> onlyAllocated = roomsService.getOnlyAllocatedDetails();
        if (rowNumber <1 || rowNumber > onlyAllocated.size()){
            System.out.println("Invalid Row Number");
        }
        return onlyAllocated.get(rowNumber-1);
    }


    public StatusMessageModel addNewRoom(int roomNumber, int roomCapacity){
        Rooms roomsModel = new Rooms();
        roomsModel.setStatus(true);
        roomsModel.setRoomNumber(roomNumber);
        roomsModel.setCapacity(roomCapacity);
        return roomsService.addNewRoomService(roomsModel);

    }

    public StatusMessageModel updateRoom(Rooms room){
        return roomsService.updateRoomService(room);
    }

    public StatusMessageModel deleteRoomController(Rooms deleteRoom){
        return roomsService.deleteRoomService(deleteRoom);
    }

    public void getUserAllocatedDetails(Users users){
        List<RoomAllocation> allocatedDetails = roomsService.getUserAllocatedDetails(users.getId());
        for (RoomAllocation allocation : allocatedDetails){
            System.out.println(allocation.getRoomId().getRoomNumber() + " \t\t"+allocation.getAllocationDate() +"\t\t\t" + allocation.getUnallocationDate());
        }
    }

    public StatusMessageModel allocatedRoom(Users selectUser , Rooms selectRoom){
        RoomAllocation allocationStudent = new RoomAllocation();
        Date getDate = new Date();
        Timestamp allocationDate = new Timestamp(getDate.getTime());
        allocationStudent.setStudentId(selectUser);
        allocationStudent.setRoomId(selectRoom);
        allocationStudent.setAllocationDate(allocationDate);
        return roomsService.setStudentAtRoom(allocationStudent);
    }

    public StatusMessageModel unallocatedRoom(RoomAllocation unallocatedDetails){
        Date date = new Date();
        Timestamp unallocationDate = new Timestamp(date.getTime());
        unallocatedDetails.setUnallocationDate(unallocationDate);
        return roomsService.unallocatedStudentFromRoom(unallocatedDetails);
    }

    public boolean unallocatedBeforeDisable(Long roomId){
        return roomsService.disableRoomUnallocationServic(roomId);
    }
}
