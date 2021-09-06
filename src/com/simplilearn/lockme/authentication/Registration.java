package com.simplilearn.lockme.authentication;

import com.simplilearn.lockme.application.Application;
import com.simplilearn.lockme.model.Users;

import java.io.PrintWriter;
import java.util.Scanner;

public class Registration {

    public static void registerUser(Scanner keyboard, Users users) {
        System.out.println("==========================================");
        System.out.println("*										 *");
        System.out.println("*   WELCOME TO REGISTRATION PAGE		 *");
        System.out.println("*										 *");
        System.out.println("==========================================");

        System.out.println("Enter Username :");
        String username = keyboard.next();
        users.setUsername(username);

        System.out.println("Enter Password :");
        String password = keyboard.next();
        users.setPassword(password);

        Application.getOutput().println(users.getUsername());
        Application.getOutput().println(users.getPassword());

        System.out.println("User Registration Successful !");
        Application.getOutput().close();
        Application.welcomeScreen();
    }
}
