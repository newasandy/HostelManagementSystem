package org.example.service;

import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.UserDAO;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.utils.PasswordUtil;

public class UserService {
    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private UserDAO userDAO ;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Users userLoginService(Users loginUser){
        Users user = userDAO.findByEmail(loginUser.getEmail());
        if (user != null){
            if (PasswordUtil.verifyPassword(loginUser.getPasswords(),user.getPasswords()) && user.getRoles().equals("USER")){
                System.out.println("User Login Successfully");
                return user;
            }else {
                System.out.println("!! Password Invalid or You Are Not User");
                return null;
            }
        }else {

            System.out.println("!! Invalid User");
            return null;
        }

    }
}
