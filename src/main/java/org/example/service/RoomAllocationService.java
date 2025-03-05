package org.example.service;

import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.RoomDAO;
import org.example.daoInterface.UserDAO;
import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;
import org.example.model.Users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class RoomAllocationService {
    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private UserDAO userDAO = new UserDAOImpl();
    private RoomDAO roomDAO = new RoomDAOImp();

    public List<RoomAllocation> getAllocatedDetails(Long userId){
        List<RoomAllocation> allocatedDetails = roomAllocationDAO.getUserAllocated(userId);
        return allocatedDetails;
    }



    public void getAllocationDetails(){
        List<RoomAllocation> roomAllocationList = roomAllocationDAO.getAll();
        System.out.println("SN\t\t Student Name \t\t\t Room Number \t\t\t Allocated Date \t\t\t Unallocated Date");
        int rowNumber =1;
        for (RoomAllocation roomAllocation : roomAllocationList){
            System.out.println(rowNumber+"\t\t"+roomAllocation.getStudentId().getFullName()+"\t\t\t"+roomAllocation.getRoomId().getRoomNumber()+"\t\t\t"+roomAllocation.getAllocationDate()+"\t\t\t"+roomAllocation.getUnallocationDate());
            rowNumber++;
        }
    }
    public void viewUnallocatedStudent(){
        List<Users> unallocatedUser = userDAO.getUnallocatedUsers();
        System.out.println("Not Allocate Student");
        System.out.println("=================================");
        int rowNumber = 1;
        for (Users user : unallocatedUser){
            System.out.println(rowNumber + "."+user.getFullName()+"\t ID: "+user.getId());
            rowNumber++;
        }
    }

    public Users getUnallocatedUserIdByRowNumber(int rowNumber){
        List<Users> unallocatedUser = userDAO.getUnallocatedUsers();
        if (rowNumber < 1 || rowNumber > unallocatedUser.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return unallocatedUser.get(rowNumber - 1);
    }

    public Rooms getRoomByRowNumber(int rowNumber){
        List<Rooms> roomsList = roomDAO.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1);
    }

    public int getRoomCapacity(int rowNumber){
        List<Rooms> roomsList = roomDAO.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1).getCapacity();
    }

    public boolean isRoomAvailable(Rooms roomId, int roomCapacity){
        Long currentOccupancy = roomAllocationDAO.getRoomOccupancy(roomId);
        System.out.println(currentOccupancy);
        return currentOccupancy < roomCapacity;
    }

    public StatusMessageModel setStudentAtRoom(RoomAllocation roomAllocation){
        if (roomAllocationDAO.add(roomAllocation)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Student Allocated at room Successfully");
        }else{
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Student not allocated at room");
        }
        return statusMessageModel;
    }

    public void getAllRoomAllocatedList(){
        List<RoomAllocation> roomAllocationList = roomAllocationDAO.getAll();
        System.out.println("SN \t S ID \t R ID \t allocated \t unallocated");
        System.out.println("=============================================");
        int rowNumber =1 ;
        for (RoomAllocation list : roomAllocationList){
            System.out.println(rowNumber +". "+list.getStudentId().getId()+"\t"+list.getRoomId().getId()+"\t"+list.getAllocationDate()+"\t"+list.getUnallocationDate());
            rowNumber++;
        }
    }

    public StatusMessageModel unallocatedStudentFromRoom(int rowNumber){
        List<RoomAllocation> roomAllocationList = roomAllocationDAO.getAll();
        RoomAllocation unallocatedStudent = roomAllocationList.get(rowNumber-1);
        Date date = new Date();
        Timestamp unallocationDate = new Timestamp(date.getTime());
        unallocatedStudent.setUnallocationDate(unallocationDate);
        if (roomAllocationDAO.update(unallocatedStudent)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Unallocated Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Unallocated Not Success");
        }
        return statusMessageModel;
    }

}
