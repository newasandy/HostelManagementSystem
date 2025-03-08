package org.example.controller;

import org.example.model.Address;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.UsersService;

import java.util.List;

public class UsersController {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private UsersService usersService = new UsersService();

    public void viewOnlyStudent(){
        List<Users> allUser = usersService.viewOnlyStudent();
        System.out.printf("%-15s %-20s %-25s%n", "User Id", "Full Name", "Email");
        System.out.println("======================================================");
        for(Users student : allUser ){
            System.out.printf("%-15s %-20s %-25s%n",student.getId(),student.getFullName(),student.getEmail());
        }
    }


    public StatusMessageModel addNewStudent(String fullName, String email, String hashPassword, String role, String country, String district, String rmcMc, int wardNo){
        Users student = new Users();
        Address address = new Address();

        student.setFullName(fullName);
        student.setEmail(email);
        student.setPasswords(hashPassword);
        student.setRoles(role);
        student.setStatus(true);

        address.setCountry(country);
        address.setDistrict(district);
        address.setRmcMc(rmcMc);
        address.setWardNo(wardNo);

        statusMessageModel = usersService.registerNewStudent(student);
        if (statusMessageModel.isStatus()){
            address.setUser(student);
            if (usersService.addUserAddress(address)){
                return statusMessageModel;
            }else {
                System.out.println("!! Not register user address");
                return statusMessageModel;
            }
        }else {
            return statusMessageModel;
        }
    }
}
