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
    private final UserDAO userDAO = new UserDAOImpl();
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final RoomDAOImp roomDAOImp = new RoomDAOImp();
    private final RoomAloocationDAO roomAloocationDAO = new RoomAllocationDAOImp();
    private final AddressDAOImp addressDAOImp = new AddressDAOImp();

    public StatusMessageModel adminLoginService(Users admin){
        Users admins = userDAO.findByEmail(admin.getEmail());
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


    public StatusMessageModel registerNewStudent(Users registerStudent){
        Users checkUser = userDAO.findByEmail(registerStudent.getEmail());
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

    public boolean addUserAddress(Address address){

        return addressDAOImp.add(address);
    }

    public StatusMessageModel addNewRoomService (Rooms newRooms){
        if (roomDAOImp.add(newRooms)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Room Added Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Not Added");
        }
        return statusMessageModel;
    }

    public void getAllUser(){
        List<Users> users = userDAO.getAll();
        System.out.println("User ID \t Full Name \t Email \t Role");
        System.out.println("=============================================");
        for (Users user : users){
            System.out.println(user.getId()+"\t"+ user.getFullName()+"\t"+user.getEmail()+"\t"+user.getRoles());
        }
    }

    public void getAllRoom(){
        List<Rooms> rooms = roomDAOImp.getAll();
        System.out.println("SN \t Room ID \t Room number \t Room Capacity");
        System.out.println("=============================================");
        int rowNumber = 1;
        for (Rooms room : rooms){
            System.out.println(rowNumber + "."+ room.getId()+"\t"+room.getRoomNumber()+"\t"+room.getCapacity());
            rowNumber++;
        }
    }

    public void viewUnalicatedStudent(){
        List<Users> unallocatedUser = userDAO.getUnallocatedUsers();
        System.out.println("Not Allocate Student");
        System.out.println("=================================");
        int rowNumber = 1;
        for (Users user : unallocatedUser){
            System.out.println(rowNumber + "."+user.getFullName()+"\t ID: "+user.getId());
            rowNumber++;
        }
    }

    public Long getUserIdByRowNumber(int rowNumber){
        List<Users> unallocatedUser = userDAO.getUnallocatedUsers();
        if (rowNumber < 1 || rowNumber > unallocatedUser.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return unallocatedUser.get(rowNumber - 1).getId();
    }

    public Long getRoomIdByRowNumber(int rowNumber){
        List<Rooms> roomsList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1).getId();
    }

    public int getRoomCapacity(int rowNumber){
        List<Rooms> roomsList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1).getCapacity();
    }

    public boolean isRoomAvailable(Long roomId, int roomCapacity){
        Long currentOccupancy = roomAloocationDAO.getRoomOccupancy(roomId);
        return currentOccupancy < roomCapacity;
    }

    public StatusMessageModel setStudentAtRoom(RoomAllocation roomAllocation){
        if (roomAloocationDAO.add(roomAllocation)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Student Allocated at room Successfully");
        }else{
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Student not allocated at room");
        }
        return statusMessageModel;
    }

    public void getAllRoomAllocatedList(){
        List<RoomAllocation> roomAllocationList = roomAloocationDAO.getAll();
        System.out.println("SN \t S ID \t R ID \t allocated \t unallocated");
        System.out.println("=============================================");
        int rowNumber =1 ;
        for (RoomAllocation list : roomAllocationList){
            System.out.println(rowNumber +". "+list.getStudentId()+"\t"+list.getRoomId()+"\t"+list.getAllocationDate()+"\t"+list.getUnallocation_date());
            rowNumber++;
        }
    }

    public StatusMessageModel unallocatedStudentFromRoom(int rowNumber){
        List<RoomAllocation> roomAllocationList = roomAloocationDAO.getAll();
        Long studentId = roomAllocationList.get(rowNumber - 1 ).getStudentId();
        Long roomId = roomAllocationList.get(rowNumber - 1 ).getRoomId();
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
