package com.simplilearn.lockme.authentication;

import com.simplilearn.lockme.application.Application;
import com.simplilearn.lockme.model.UserCredentials;

import java.io.*;
import java.util.Scanner;

public class Authentication {

    private static PrintWriter lockerOutput;

    public static void lockerOptions(String inpUsername, Scanner keyboard,UserCredentials userCredentials) {
        System.out.println("=================================");
        System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
        System.out.println("2 . STORED CREDENTIALS ");
        System.out.println("3 . REMOVE CREDENTIALS ");
        System.out.println("4 . LOGOUT ");
        System.out.println("=================================");
        createLockerforUser(inpUsername);
        int option = keyboard.nextInt();
        switch(option) {
            case 1 :
                fetchCredentials(inpUsername,keyboard,userCredentials);
                break;
            case 2 :
                storeCredentials(inpUsername,keyboard,userCredentials);
                break;
            case 3 :
                removeCredentials(inpUsername,keyboard,userCredentials);
                break;
            case 4 :
                logout();
                break;
            default :
                System.out.println("Please select 1,2,3 Or 4");
                lockerOptions(inpUsername,keyboard,userCredentials);
                break;
        }
        Application.getLockerInput().close();
    }

    //Create separate file for per user
    private static void createLockerforUser(String inpUsername) {
        File userFile = new File("users/"+inpUsername+".txt");
        try {
            lockerOutput = new PrintWriter(new FileWriter(userFile, true));
        }catch (IOException e) {
            System.out.println("404 : File Not Found ");
        }
    }

    //fetch credentials
    public static void fetchCredentials(String inpUsername,Scanner keyboard,UserCredentials userCredentials){
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*   WELCOME TO DIGITAL LOCKER 	 		 *");
        System.out.println("*   YOUR CREDENTIALS ARE 	 			 *");
        System.out.println("*										 *");
        System.out.println("==========================================");
        System.out.println(inpUsername);
        try {
            File lockerFile = new File("users/"+inpUsername+".txt");
            Scanner lockerInput = new Scanner(lockerFile);
            while(lockerInput.hasNext()) {
                if(lockerInput.next().equals(inpUsername)) {
                    System.out.println("Site Name: "+lockerInput.next());
                    System.out.println("User Name: "+lockerInput.next());
                    System.out.println("User Password: "+lockerInput.next());
                }
            }
        }catch (IOException e) {
            System.out.println("File Not Found !!");
        }
        lockerOptions(inpUsername,keyboard,userCredentials);
    }

    //store credentials
    public static void storeCredentials(String loggedInUser, Scanner keyboard,UserCredentials userCredentials) {
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
        lockerOptions(loggedInUser,keyboard,userCredentials);
    }

    //remove credentials
    public static void removeCredentials(String inpUsername,Scanner keyboard,UserCredentials userCredentials) {
        File userFile = new File("users/"+inpUsername+".txt");
        try{
            lockerOutput = new PrintWriter(userFile);
            lockerOutput.write("");
            lockerOutput.close();
            System.out.println("YOUR CREDENTIALS HAS BEEN REMOVED!");
        }catch (IOException e){
            System.out.println("File Not Found !!");
        }

        lockerOptions(inpUsername,keyboard,userCredentials);
    }
    //logout user
    private static void logout() {
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*  User Logout Successfully!	 		 *");
        System.out.println("*										 *");
        System.out.println("==========================================");
        Application.welcomeScreen();
    }
}
