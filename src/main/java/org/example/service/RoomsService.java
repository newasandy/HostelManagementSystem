package org.example.service;

import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.RoomDAO;
import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class RoomsService {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomAllocationDAO roomAllocationDAO ;
    private RoomDAO roomDAO ;

    public RoomsService(RoomDAO roomDAO, RoomAllocationDAO roomAllocationDAO){
        this.roomDAO=roomDAO;
        this.roomAllocationDAO=roomAllocationDAO;
    }

    public List<Rooms> getAllRoom(){
        return roomDAO.getAll();
    }
    public List<Rooms> getAvailableRoom(){
        return roomDAO.getAvailableRoom();
    }

    public StatusMessageModel addNewRoomService (Rooms newRooms){
        Rooms checkRoom = roomDAO.findByRoomNumber(newRooms.getRoomNumber());
        if (checkRoom == null){
            if (roomDAO.add(newRooms)){
                statusMessageModel.setStatus(true);
                statusMessageModel.setMessage("Room Added Successfully");
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Room Not Added");
            }
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Already Exist");
        }
        return statusMessageModel;
    }

    public StatusMessageModel updateRoomService(Rooms room){
        Rooms getRoom = roomDAO.getById(room.getId());
        Long getOccupancy = roomAllocationDAO.getRoomOccupancy(room);
        if (getRoom.getRoomNumber() == room.getRoomNumber() && getOccupancy <= room.getCapacity()){
            if (roomDAO.update(room)){
                statusMessageModel.setStatus(true);
                statusMessageModel.setMessage("Update Room Successfully");
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Not Update Room");
            }
        } else if (getOccupancy <= room.getCapacity()) {
            Rooms checkRoom = roomDAO.findByRoomNumber(room.getRoomNumber());
            if (checkRoom == null ){
                if (roomDAO.update(room)){
                    statusMessageModel.setStatus(true);
                    statusMessageModel.setMessage("Update Room Successfully");
                }else {
                    statusMessageModel.setStatus(false);
                    statusMessageModel.setMessage("!! Not Update Room");
                }
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Room Number Already Exist");
            }
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Given Room Capacity Is less than allocation count");
        }
        return statusMessageModel;
    }

    public StatusMessageModel deleteRoomService(Rooms room){
        if (roomDAO.update(room)){
                statusMessageModel.setStatus(true);
                statusMessageModel.setMessage("Disable Room Successfully");
        }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Room Not Disable");
        }
        return statusMessageModel;
    }

    public List<RoomAllocation> getUserAllocatedDetails(Long userId){
        return roomAllocationDAO.getUserAllocated(userId);
    }

    public List<RoomAllocation> getOnlyAllocatedDetails(){
        return roomAllocationDAO.getOnlyAllocatedDetails();
    }

    public List<RoomAllocation> getAllRoomAllocatedDetails(){
        return roomAllocationDAO.getAll();
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

    public boolean disableRoomUnallocationServic(Long roomId){
        Date date = new Date();
        Timestamp unallocatedDate = new Timestamp(date.getTime());
        if (roomAllocationDAO.disableRoomUnallocatedStudent(roomId,unallocatedDate)){
            return true;
        }else {
            return false;
        }
    }
}
