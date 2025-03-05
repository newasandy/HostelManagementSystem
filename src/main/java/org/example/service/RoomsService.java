package org.example.service;

import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.RoomDAO;
import org.example.model.Rooms;
import org.example.model.StatusMessageModel;

import java.util.List;

public class RoomsService {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    private RoomDAO roomDAO = new RoomDAOImp();

    public void getAllRoom(){
        List<Rooms> rooms = roomDAO.getAll();
        System.out.println("SN \t Room ID \t Room number \t Room Capacity");
        System.out.println("=============================================");
        int rowNumber = 1;
        for (Rooms room : rooms){
            System.out.println(rowNumber + "."+ room.getId()+"\t"+room.getRoomNumber()+"\t"+room.getCapacity());
            rowNumber++;
        }
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
            throw new IllegalArgumentException("Invalid Row Number");
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
        if (roomDAO.delete(rooms.get(rowNumber-1).getId())){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Delete Room Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Not Delete");
        }

        return statusMessageModel;
    }


}
