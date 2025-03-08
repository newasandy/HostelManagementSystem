package org.example.view;

import org.example.daoImplementation.AddressDAOImp;
import org.example.daoImplementation.UserDAOImpl;
import org.example.daoInterface.UserDAO;
import org.example.model.Users;
import org.example.service.UsersService;
import org.example.service.AuthenticationService;

import java.util.Scanner;

public class Main {


    private UserDAO userDAO = new UserDAOImpl();
    private AddressDAOImp addressDAOImp = new AddressDAOImp();
    private final UsersService usersService = new UsersService(userDAO,addressDAOImp);
    private final AdminView adminView = new AdminView();
    private AuthenticationService authenticationService = new AuthenticationService(userDAO);
    private final GeneralUserView generalUserView = new GeneralUserView();
    private static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Main main = new Main();
        while (true){
            System.out.println("Select User For Login: \n 1. Admin Login \n 2. Student Login \n 3. Exit");
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1){
                main.adminLogin();
            } else if (i == 2){
                main.userLogin();
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

            Users loginAdmin = usersService.adminLoginService(admin);
            if(loginAdmin != null){
                adminView.loginedAdminService(loginAdmin);
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

    public void userLogin(){
        Users user = new Users();
        while (true){
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            user.setEmail(email);
            user.setPasswords(password);

            Users loginUser = authenticationService.userLoginService(user);
            if(loginUser != null){
                generalUserView.userLoginService(loginUser);
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