package org.example.controller;

import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.service.AdminService;
import org.example.service.UserService;

import java.util.Scanner;

public class MainController {

    private StatusMessageModel statusMessageModel = new StatusMessageModel();
    private final AdminService adminService = new AdminService();
    private final AdminController adminController = new AdminController();
    private final UserService userService = new UserService();
    private final UserController userController = new UserController();
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MainController mainController = new MainController();
        while (true){
            System.out.println("Select User For Login: \n 1. Admin Login \n 2. Student Login \n 3. Exit");
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1){
                mainController.adminLogin();
            } else if (i == 2){
                mainController.userLogin();
            }else if (i ==3) {
                System.out.println("have a good day");
                break;
            }
        }
    }
    public void adminLogin(){
        Users admin = new Users();
        while (true){
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            admin.setEmail(email);
            admin.setPasswords(password);

            statusMessageModel = adminService.adminLoginService(admin);
            if(statusMessageModel.isStatus()){
                System.out.println(statusMessageModel.getMessage());
                adminController.loginedAdminService();
                break;
            }else{
                System.out.println(statusMessageModel.getMessage());
                System.out.println("1. Re-Enter");
                System.out.println("2. Exit");
                String option = sc.nextLine();
                if (option.equals("2")){
                    break;
                }
            }
        }
    }

    public void userLogin(){
        Users user = new Users();
        while (true){
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            user.setEmail(email);
            user.setPasswords(password);

            Users loginUser = userService.userLoginService(user);
            if(loginUser != null){
                userController.userLoginService(loginUser);
                break;
            }else{
                System.out.println("1. Re-Enter");
                System.out.println("2. Exit");
                String option = sc.nextLine();
                if (option.equals("2")){
                    break;
                }
            }
        }
    }
}