package org.example.service;

import org.example.daoImplementation.*;
import org.example.daoInterface.UserDAO;
import org.example.model.*;
import org.example.utils.PasswordUtil;
import java.util.List;

public class AdminService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final StatusMessageModel statusMessageModel = new StatusMessageModel();
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
        Users getUser = userDAO.getById(user.getUser().getId());
        if (getUser.getEmail().equals(user.getUser().getEmail())){
            if (userDAO.update(user.getUser())){
                if (addressDAOImp.update(user)){
                    statusMessageModel.setStatus(true);
                    statusMessageModel.setMessage("User Details Update Successfully");
                }
            }else {
                statusMessageModel.setStatus(false);
                statusMessageModel.setMessage("!! User Details Not Updated");
            }
        }else {
            Users checkUser = userDAO.findByEmail(user.getUser().getEmail());
            if (checkUser == null){
                if (userDAO.update(user.getUser())){
                    if (addressDAOImp.update(user)){
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

}
