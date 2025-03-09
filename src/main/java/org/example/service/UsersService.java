package org.example.service;

import org.example.daoImplementation.*;
import org.example.daoInterface.UserDAO;
import org.example.model.*;
import java.util.List;

public class UsersService {
    private final UserDAO userDAO;
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final AddressDAOImp addressDAOImp ;

    public UsersService(UserDAO userDAO , AddressDAOImp addressDAOImp){
        this.userDAO=userDAO;
        this.addressDAOImp=addressDAOImp;
    }


    public StatusMessageModel registerNewStudent(Users registerStudent){
        Users checkUser = userDAO.findByEmail(registerStudent.getEmail());
        if (checkUser == null){
            userDAO.add(registerStudent);
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("User Register Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("User Already Exist");
        }
        return statusMessageModel;
    }

    public boolean addUserAddress(Address address){
        return addressDAOImp.add(address);
    }

    public List<Users> viewOnlyStudent(){
        return userDAO.getOnlyStudent();
    }

    public Address getUserDetailByRowNumber(int rowNumber){
        List<Address> users = addressDAOImp.getAll();
        if (rowNumber < 1 || rowNumber > users.size()){
            System.out.println("Invalid Row Number");
        }
        return users.get(rowNumber-1);
    }

    public List<Users> getAllUser(){
        return userDAO.getAll();
    }

    public StatusMessageModel updateUserDetails(Users user){
        Users updateUserData = userDAO.getById(user.getId());
        if (updateUserData.getEmail().equals(user.getEmail())){
            if (userDAO.update(user)){
                if (addressDAOImp.update(user.getAddress())){
                    statusMessageModel.setStatus(true);
                    statusMessageModel.setMessage("User Details Update Successfully");
                }
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! User Details Not Updated");
            }
        }else {
            Users checkUser = userDAO.findByEmail(user.getEmail());
            if (checkUser == null){
                if (userDAO.update(user)){
                    if (addressDAOImp.update(user.getAddress())){
                        statusMessageModel.setStatus(true);
                        statusMessageModel.setMessage("User Details Update Successfully");
                    }
                }else {
                    statusMessageModel.setStatus(false);
                    statusMessageModel.setMessage("!! User Details Not Updated");
                }
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! User Already Exist");
            }
        }
        return statusMessageModel;
    }


    public StatusMessageModel deleteUserService(Users deleteUser){
        if (userDAO.delete(deleteUser.getId())){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Delete user Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Delete User");
        }
        return statusMessageModel;
    }

    public List<Users> getUnallocatedStudent(){
        return userDAO.getUnallocatedUsers();
    }

}
