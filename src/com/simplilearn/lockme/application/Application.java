package com.simplilearn.lockme.application;

import java.io.*;
import java.util.Scanner;

import com.simplilearn.lockme.authentication.Login;
import com.simplilearn.lockme.authentication.Registration;
import com.simplilearn.lockme.model.UserCredentials;
import com.simplilearn.lockme.model.Users;

public class Application {

	//input data
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	//output data 
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	//model to store data.
	private static Users users;
	private static UserCredentials userCredentials;

	public static UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public static void setUserCredentials(UserCredentials userCredentials) {
		Application.userCredentials = userCredentials;
	}

	public static PrintWriter getOutput() {
		return output;
	}

	public static void setOutput(PrintWriter output) {
		Application.output = output;
	}

	public static PrintWriter getLockerOutput() {
		return lockerOutput;
	}

	public static void setLockerOutput(PrintWriter lockerOutput) {
		Application.lockerOutput = lockerOutput;
	}

	public static Scanner getLockerInput() {
		return lockerInput;
	}



	public static void main(String[] args) {
		welcomeScreen();
	}

	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*										 *");
		System.out.println("*   Welcome To LockMe.com				 *");
		System.out.println("*   Your Personal Digital Locker	 	 *");
		System.out.println("*										 *");
		System.out.println("==========================================");
		initApp();
		signInOptions();
	}
	public static void signInOptions() {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				Registration.registerUser(keyboard,users);
				break;
			case 2 :
				Login.loginUser(keyboard,input,userCredentials,lockerOutput);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				welcomeScreen();
				break;
		}
		keyboard.close();
		input.close();
	}

	public static void initApp() {

		File  dbFile = new File("lockme-user.txt");
		File  lockerFile = new File("locker-file.txt");
		
		try {
			//read data from db file
			input = new Scanner(dbFile);
			
			//read data from locker file
			lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboard = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users = new Users();
			userCredentials  = new UserCredentials();

		} catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}
	}
}
