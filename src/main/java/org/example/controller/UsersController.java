package org.example.controller;

import org.example.daoImplementation.AddressDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.UserDAO;
import org.example.model.Address;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.UsersService;

import java.util.List;

public class UsersController {
    private UserDAO userDAO = new UserDAOImpl();
    private AddressDAOImp addressDAOImp = new AddressDAOImp();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private UsersService usersService = new UsersService(userDAO, addressDAOImp);

    public void viewOnlyStudent(){
        List<Users> students = usersService.viewOnlyStudent();
        System.out.printf("%-5s %-15s %-20s %-25s %-20s %-20s %-25s %-10s%n","SN", "User Id", "Full Name", "Email","Country","District","RMC/MC","Ward No");
        System.out.println("======================================================");
        int sn = 1;
        for(Users student : students){
            System.out.printf("%-5s %-15s %-20s %-25s %-20s %-20s %-25s %-10s%n",sn ,student.getId(),student.getFullName(),student.getEmail(),student.getAddress().getCountry(),student.getAddress().getDistrict(),student.getAddress().getRmcMc(),student.getAddress().getWardNo());
            sn++;
        }
    }

    public void getAllUserDetails(){
        List<Users> allUser = usersService.getAllUser();
        System.out.printf("%-5s %-15s %-20s %-25s %-20s %-20s %-25s %-10s%n","SN", "User Id", "Full Name", "Email","Country","District","RMC/MC","Ward No");
        System.out.println("======================================================");
        int sn = 1;
        for(Users student : allUser ){
            System.out.printf("%-5s %-15s %-20s %-25s %-20s %-20s %-25s %-10s%n",sn ,student.getId(),student.getFullName(),student.getEmail(),student.getAddress().getCountry(),student.getAddress().getDistrict(),student.getAddress().getRmcMc(),student.getAddress().getWardNo());
            sn++;
        }
    }

    public Users getUserDetailByRowNumber(int rowNumber){
        List<Users> allUser = usersService.getAllUser();
        if (rowNumber <1 || rowNumber >allUser.size()){
            System.out.println("Invalid Row Number");
            return null;
        }else {
            return allUser.get(rowNumber-1);
        }
    }

    public boolean viewUnallocatedStudent(){
        List<Users> unallocatedUsers = usersService.getUnallocatedStudent();
        if (unallocatedUsers.isEmpty()){
            return false;
        }else {
            int sn = 1;
            for (Users user : unallocatedUsers){
                System.out.println(sn +"\t"+user.getId()+"\t\t"+user.getFullName()+"\t\t"+user.getEmail()+"\t\t"+user.getRoles());
            }
            return true;
        }

    }

    public Users getUnallocatedStudentByRow(int rowNumber){
        List<Users> unallocatedUsers = usersService.getUnallocatedStudent();
        if (rowNumber <1 || rowNumber >unallocatedUsers.size()){
            System.out.println("Invalid Row Number");
            return null;
        }else {
            return unallocatedUsers.get(rowNumber-1);
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

    public StatusMessageModel updateUserDetails(Users userUpdateDetails){

        return usersService.updateUserDetails(userUpdateDetails);
    }

    public StatusMessageModel deleteUser(int rowNumber){
        Users deleteUser = getUserDetailByRowNumber(rowNumber);
        return statusMessageModel = usersService.deleteUserService(deleteUser);
    }
}
