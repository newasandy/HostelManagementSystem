package org.example.service;

import org.example.daoImplementation.AddressDAOImp;
import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.RoomAloocationDAO;
import org.example.daoInterface.UserDAO;
import org.example.model.*;
import org.example.utils.PasswordUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AdminService {
    boolean status;
    private final UserDAO userDAO = new UserDAOImpl();
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final RoomDAOImp roomDAOImp = new RoomDAOImp();
    private final RoomAloocationDAO roomAloocationDAO = new RoomAllocationDAOImp();
    private final AddressDAOImp addressDAOImp = new AddressDAOImp();

    public StatusMessageModel adminLoginService(UsersModel admin){
        UsersModel admins = userDAO.findByEmail(admin.getEmail());
        if (admins != null){
            if (PasswordUtil.verifyPassword(admin.getPasswords(),admins.getPasswords()) && admins.getRoles().equals("ADMIN")){
                statusMessageModel.setStatus(true);
                statusMessageModel.setMessage("Admin Login Successfully");
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! Invalid Password or You are not Admin");
            }
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Invalid User");
        }
        return statusMessageModel;
    }


    public StatusMessageModel registerNewStudent(UsersModel registerStudent){
        UsersModel checkUser = userDAO.findByEmail(registerStudent.getEmail());
        if (checkUser == null){
            userDAO.add(registerStudent);
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("User Register Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("User Already Exits");
        }
        return statusMessageModel;
    }

    public boolean addUserAddress(AddressModel address){

        return addressDAOImp.add(address);
    }

    public StatusMessageModel addNewRoomService (RoomModel newRoom){
        if (roomDAOImp.add(newRoom)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Room Added Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Not Added");
        }
        return statusMessageModel;
    }

    public void getAllUser(){
        List<UsersModel> users = userDAO.getAll();
        for (UsersModel user : users){
            System.out.println(user.getId()+"\t"+ user.getFull_name()+"\t"+user.getEmail()+"\t"+user.getRoles());
        }
    }

    public void getAllRoom(){
        List<RoomModel> rooms = roomDAOImp.getAll();
        int rowNumber = 1;
        for (RoomModel room : rooms){
            System.out.println(rowNumber + "."+ room.getId()+"\t"+room.getRoom_number()+"\t"+room.getCapacity());
            rowNumber++;
        }
    }

    public void viewUnalicatedStudent(){
        List<UsersModel> unallocatedUser = userDAO.getUnallocatedUsers();
        System.out.println("Not Allocate Student");
        System.out.println("=================================");
        int rowNumber = 1;
        for (UsersModel user : unallocatedUser){
            System.out.println(rowNumber + "."+user.getFull_name()+"\t ID: "+user.getId());
            rowNumber++;
        }
    }

    public Long getUserIdByRowNumber(int rowNumber){
        List<UsersModel> unallocatedUser = userDAO.getUnallocatedUsers();
        if (rowNumber < 1 || rowNumber > unallocatedUser.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return unallocatedUser.get(rowNumber - 1).getId();
    }

    public Long getRoomIdByRowNumber(int rowNumber){
        List<RoomModel> roomList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomList.get(rowNumber - 1).getId();
    }

    public int getRoomCapacity(int rowNumber){
        List<RoomModel> roomList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomList.get(rowNumber - 1).getCapacity();
    }

    public boolean isRoomAvailable(Long roomId, int roomCapacity){
        Long currentOccupancy = roomAloocationDAO.getRoomOccupancy(roomId);
        return currentOccupancy < roomCapacity;
    }

    public StatusMessageModel setStudentAtRoom(RoomAllocationModel roomAllocationModel){
        if (roomAloocationDAO.add(roomAllocationModel)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Student Allocated at room Successfully");
        }else{
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Student not allocated at room");
        }
        return statusMessageModel;
    }

    public void getAllRoomAllocatedList(){
        List<RoomAllocationModel> roomAllocationList = roomAloocationDAO.getAll();
        int rowNumber =1 ;
        for (RoomAllocationModel list : roomAllocationList){
            System.out.println(rowNumber +". "+list.getStudent_id()+"\t"+list.getRoom_id()+"\t"+list.getAllocation_date());
            rowNumber++;
        }
    }

    public StatusMessageModel unallocatedStudentFromRoom(int rowNumber){
        List<RoomAllocationModel> roomAllocationList = roomAloocationDAO.getAll();
        Long studentId = roomAllocationList.get(rowNumber - 1 ).getStudent_id();
        Long roomId = roomAllocationList.get(rowNumber - 1 ).getRoom_id();
        Date date = new Date();
        Timestamp unallocationDate = new Timestamp(date.getTime());
        if (roomAloocationDAO.unallocateStudent(studentId,roomId,unallocationDate)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Unallocated Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Unallocated Not Success");
        }
        return statusMessageModel;
    }

}
