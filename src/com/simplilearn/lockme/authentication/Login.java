package com.simplilearn.lockme.authentication;

import com.simplilearn.lockme.application.Application;
import com.simplilearn.lockme.model.UserCredentials;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Login {

    public static void loginUser(Scanner keyboard, Scanner input,UserCredentials userCredentials, PrintWriter lockerOutput) {
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*   WELCOME TO LOGIN PAGE	 			 *");
        System.out.println("*										 *");
        System.out.println("==========================================");
        System.out.println("Enter Username :");
        String inpUsername = keyboard.next();
        boolean found = false;
        while(input.hasNext() && !found) {
            if(input.next().equals(inpUsername)) {
                System.out.println("Enter Password :");
                String inpPassword = keyboard.next();
                if(input.next().equals(inpPassword)) {
                    System.out.println("================================");
                    System.out.println("* Login Successful ! 200OK     *");
                    System.out.println("================================");
                    found = true;
                    Authentication.lockerOptions(inpUsername,keyboard,userCredentials,lockerOutput);
                    break;
                }
            }
        }
        if(!found) {
            System.out.println("User Not Found : Login Failure : 404");
            System.out.println("================================");
            System.out.println("* Kindly register !!!          *");
            System.out.println("================================");
            Application.welcomeScreen();
        }
    }
}
