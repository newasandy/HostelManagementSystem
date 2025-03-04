package org.example.service;

import org.example.daoImplementation.*;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.daoInterface.UserDAO;
import org.example.daoInterface.VisitorsDAO;
import org.example.model.*;
import org.example.utils.PasswordUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AdminService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final RoomDAOImp roomDAOImp = new RoomDAOImp();
    private final RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    private final AddressDAOImp addressDAOImp = new AddressDAOImp();
    private final VisitorsDAO visitorsDAO = new VisitorsDAOImp();

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

    public void viewOnlyStudent(){
        List<Users> students = userDAO.getOnlyStudent();
        System.out.printf("%-15s %-20s %-25s%n", "User Id", "Full Name", "Email");
        System.out.println("======================================================");
        for(Users student : students ){
            System.out.printf("%-15s %-20s %-25s%n",student.getId(),student.getFullName(),student.getEmail());
        }
    }

    public Address getUserDetailByRowNumber(int rowNumber){
        List<Address> users = addressDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > users.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return users.get(rowNumber-1);

    }

    public void getAllUserAndAddress(){
        List<Address> users = addressDAOImp.getAll();
        int rowNumber = 1;
        for (Address user : users){
            System.out.println(rowNumber+"\t"+user.getUser().getFullName()+"\t\t"+user.getUser().getEmail() +"\t\t" +user.getUser().getRoles()+"\t\t" +user.getUser().isStatus());
            rowNumber++;
        }
    }

    public StatusMessageModel updateUserDetails(Address user){
        if (addressDAOImp.update(user)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("User Details Update Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! User Details Not Updated");
        }
        return statusMessageModel;
    }

    public StatusMessageModel deleteUserService(int rowNumber){
        List<Users> users = userDAO.getAll();
        if (rowNumber < 0 || rowNumber > users.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        if (userDAO.delete(users.get(rowNumber - 1 ).getId())){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Delete user Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Delete User");
        }
        return statusMessageModel;
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

    public Rooms getRoomByRowNumber(int rowNumber){
        List<Rooms> rooms = roomDAOImp.getAll();
        if (rowNumber <0 || rowNumber > rooms.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return rooms.get(rowNumber - 1);
    }

    public StatusMessageModel updateRoomService(Rooms room){
        if (roomDAOImp.update(room)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Update Room Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Update Room");
        }
        return statusMessageModel;
    }

    public StatusMessageModel deleteRoomService(int rowNumber){
        List<Rooms> rooms = roomDAOImp.getAll();
        if (rowNumber <0 || rowNumber > rooms.size()){
            System.out.println("Invalid Row Number");
        }
        if (roomDAOImp.delete(rooms.get(rowNumber-1).getId())){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Delete Room Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Not Delete");
        }

        return statusMessageModel;
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

    public Rooms getRoomIdByRowNumber(int rowNumber){
        List<Rooms> roomsList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1);
    }

    public int getRoomCapacity(int rowNumber){
        List<Rooms> roomsList = roomDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > roomsList.size()){
            throw new IllegalArgumentException("Invalid Row Number");
        }
        return roomsList.get(rowNumber - 1).getCapacity();
    }

    public boolean isRoomAvailable(Rooms roomId, int roomCapacity){
        Long currentOccupancy = roomAllocationDAO.getRoomOccupancy(roomId);
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
        Users studentId = roomAllocationList.get(rowNumber - 1 ).getStudentId();
        Rooms roomId = roomAllocationList.get(rowNumber - 1 ).getRoomId();
        Date date = new Date();
        Timestamp unallocationDate = new Timestamp(date.getTime());
        if (roomAllocationDAO.unallocateStudent(studentId,roomId,unallocationDate)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Unallocated Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Unallocated Not Success");
        }
        return statusMessageModel;
    }

    public void getAllVisitor(){
        List<Visitors> visitors = visitorsDAO.getAll();
        int rowNumber = 1;
        for(Visitors visitor : visitors){
            System.out.println(rowNumber +"\t\t"+visitor.getStudentId().getFullName()+"\t\t\t"+visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
            rowNumber++;
        }
    }

    public StatusMessageModel addVisitorService(Visitors visitor){
        if (visitorsDAO.add(visitor)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Add Visitor Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Added visitor");
        }
        return statusMessageModel;
    }

    public StatusMessageModel exitVisitorUpdate(int rowNumber){
        List<Visitors> visitors = visitorsDAO.getAll();
        if (rowNumber < 0 || rowNumber > visitors.size()){
            System.out.println("Invalid Row Number");
        }
        Date date = new Date();
        Timestamp exitDate = new Timestamp(date.getTime());
        Visitors visitor = visitors.get(rowNumber-1);
        visitor.setExitDatetime(exitDate);

        if (visitorsDAO.update(visitor)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Visitor Update Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Can't Update Visitor");
        }
        return statusMessageModel;
    }

}
