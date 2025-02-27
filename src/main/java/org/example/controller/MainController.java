package org.example.controller;



import java.util.Date;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Date dt = new Date();
        while (true){
            System.out.println("Select user: \n 1. Admin \n 2. Student \n 3. Exit");
            int i = sc.nextInt();
            if (i == 1){
                System.out.println("hello");
            } else if (i == 2){
                System.out.println("this is student"+ dt);
            }else if (i ==3) {
                System.out.println("have a good day");
                break;
            }
        }

    }
}