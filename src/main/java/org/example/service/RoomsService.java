package org.example.service;

import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.RoomDAO;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class RoomsService {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    private RoomDAO roomDAO = new RoomDAOImp();

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


    public Rooms getRoomByRowNumber(int rowNumber){
        List<Rooms> rooms = roomDAO.getAll();
        if (rowNumber <0 || rowNumber > rooms.size()){
            System.out.println("invalid Row Number");
        }
        return rooms.get(rowNumber - 1);
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

    public StatusMessageModel deleteRoomService(int rowNumber){
        List<Rooms> rooms = roomDAO.getAll();
        if (rowNumber <= 0 || rowNumber > rooms.size()){
            System.out.println("Invalid Row Number");
        }
        Rooms room = rooms.get(rowNumber-1);
        room.setStatus(false);
        Date date = new Date();
        Timestamp unallocatedDate = new Timestamp(date.getTime());
        if (roomAllocationDAO.disableRoomUnallocatedStudent(room.getId(),unallocatedDate)){
            if (roomDAO.update(room)){
                statusMessageModel.setStatus(true);
                statusMessageModel.setMessage("Disable Room Successfully");
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Room Not Disable");
            }
//            System.out.println("unallocated");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Student Unallocated Failed");
        }
        return statusMessageModel;
    }


}
