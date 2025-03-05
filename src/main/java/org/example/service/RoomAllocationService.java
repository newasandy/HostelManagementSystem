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
    public List<Users> getUnallocatedStudent(){
        return userDAO.getUnallocatedUsers();
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

    public List<RoomAllocation> getAllRoomAllocatedList(){
        return roomAllocationDAO.getAll();

    }

    public StatusMessageModel unallocatedStudentFromRoom(RoomAllocation unallocatedUser){
        if (roomAllocationDAO.update(unallocatedUser)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Unallocated Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Unallocated Not Success");
        }
        return statusMessageModel;
    }



}
