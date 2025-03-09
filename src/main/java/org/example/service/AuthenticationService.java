package org.example.service;

import org.example.daoInterface.UserDAO;
import org.example.model.Users;
import org.example.utils.PasswordUtil;

public class AuthenticationService {
    private UserDAO userDAO ;

    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Users loginService(Users loginUser){
        Users user = userDAO.findByEmail(loginUser.getEmail());
        if (user != null){
            if (PasswordUtil.verifyPassword(loginUser.getPasswords(),user.getPasswords())){

                System.out.println("Hi, "+user.getFullName()+" Welcome Back");
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
