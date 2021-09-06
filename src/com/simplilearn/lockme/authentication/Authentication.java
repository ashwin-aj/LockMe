package com.simplilearn.lockme.authentication;

import com.simplilearn.lockme.application.Application;
import com.simplilearn.lockme.model.UserCredentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Authentication {

    public static void lockerOptions(String inpUsername, Scanner keyboard,UserCredentials userCredentials, PrintWriter lockerOutput) {
        System.out.println("=================================");
        System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
        System.out.println("2 . STORED CREDENTIALS ");
        System.out.println("3 . LOGOUT ");
        System.out.println("=================================");
        int option = keyboard.nextInt();
        switch(option) {
            case 1 :
                fetchCredentials(inpUsername,keyboard,userCredentials,lockerOutput);
                break;
            case 2 :
                storeCredentials(inpUsername,keyboard,userCredentials,lockerOutput);
                break;
            case 3 :
                logout();
                break;
            default :
                System.out.println("Please select 1,2 Or 3");
                lockerOptions(inpUsername,keyboard,userCredentials,lockerOutput);
                break;
        }
        Application.getLockerInput().close();
    }

    private static void logout() {
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*  User Logout Successfully!	 		 *");
        System.out.println("*										 *");
        System.out.println("==========================================");
        Application.welcomeScreen();
    }

    //fetch credentials
    public static void fetchCredentials(String inpUsername,Scanner keyboard,UserCredentials userCredentials, PrintWriter lockerOutput){
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*   WELCOME TO DIGITAL LOCKER 	 		 *");
        System.out.println("*   YOUR CREDENTIALS ARE 	 			 *");
        System.out.println("*										 *");
        System.out.println("==========================================");
        System.out.println(inpUsername);
        try {
            File lockerFile = new File("locker-file.txt");
            Scanner lockerInput = new Scanner(lockerFile);
            while(lockerInput.hasNext()) {
//			System.out.println(lockerInput.hasNext());
                if(lockerInput.next().equals(inpUsername)) {
                    System.out.println("Site Name: "+lockerInput.next());
                    System.out.println("User Name: "+lockerInput.next());
                    System.out.println("User Password: "+lockerInput.next());
                }
            }
        }catch (IOException e) {
            System.out.println("File Not Found !!");
        }
        lockerOptions(inpUsername,keyboard,userCredentials,lockerOutput);
    }

    //store credentials
    public static void storeCredentials(String loggedInUser, Scanner keyboard,UserCredentials userCredentials, PrintWriter lockerOutput) {
        System.out.println("==========================================================");
        System.out.println("*													     *");
        System.out.println("* WELCOME TO DIGITAL LOCKER STORE YOUR CREDENTIALS HERE	 *");
        System.out.println("*													     *");
        System.out.println("==========================================================");

        userCredentials.setLoggedInUser(loggedInUser);

        System.out.println("Enter Site Name :");
        String siteName = keyboard.next();
        userCredentials.setSiteName(siteName);

        System.out.println("Enter Username :");
        String username = keyboard.next();
        userCredentials.setUsername(username);

        System.out.println("Enter Password :");
        String password = keyboard.next();
        userCredentials.setPassword(password);

        lockerOutput.println(userCredentials.getLoggedInUser());
        lockerOutput.println(userCredentials.getSiteName());
        lockerOutput.println(userCredentials.getUsername());
        lockerOutput.println(userCredentials.getPassword());
        lockerOutput.close();
        System.out.println("YOUR CREDENTIALS ARE STORED AND SECURED!");
        lockerOptions(loggedInUser,keyboard,userCredentials,lockerOutput);
    }
}
