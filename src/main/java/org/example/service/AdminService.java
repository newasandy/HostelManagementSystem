package org.example.service;

import org.example.daoImplementation.AddressDAOImp;
import org.example.daoImplementation.RoomDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.UserDAO;
import org.example.model.AddressModel;
import org.example.model.RoomModel;
import org.example.model.StatusMessageModel;
import org.example.model.UsersModel;
import org.example.utils.PasswordUtil;

public class AdminService {
    boolean status;
    private final UserDAO getUser = new UserDAOImpl();
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
    public boolean adminLoginService(UsersModel admin){


        UsersModel admins = getUser.findByEmail(admin.getEmail());

        if (admins != null){
            if (PasswordUtil.verifyPassword(admin.getPasswords(),admins.getPasswords()) && admins.getRoles().equals("ADMIN")){
                status =true;
            }else {
                status = false;
            }
        }else {
            status = false;
        }
        return true;
    }

    public StatusMessageModel registerNewStudent(UsersModel registerStudent){


        UsersModel checkUser = getUser.findByEmail(registerStudent.getEmail());
        UserDAOImpl user = new UserDAOImpl();
        if (checkUser == null){
            user.add(registerStudent);
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("User Register Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("User Already Exits");
        }
        return statusMessageModel;
    }

    public boolean addUserAddress(AddressModel address){
        AddressDAOImp addressAdd = new AddressDAOImp();
        return addressAdd.add(address);
    }

    public StatusMessageModel addNewRoomService (RoomModel newRoom){
        RoomDAOImp addRoom = new RoomDAOImp();
        if (addRoom.add(newRoom)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Room Added Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Room Not Added");
        }
        return statusMessageModel;
    }

}
