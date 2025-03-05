package org.example.service;

import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.UserDAO;
import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.utils.PasswordUtil;

public class UserService {
    private final UserDAO userDAO = new UserDAOImpl();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();



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
