package com.zero.controller;

import java.util.Scanner;
import com.zero.models.User;
import com.zero.services.AuthService;
import com.zero.services.AuthServiceImpl;
import com.zero.services.UserService;
import com.zero.services.UserServiceImpl;
import com.zero.exceptions.AuthException;
import com.zero.exceptions.UserNotFoundException;


public class MenuScreen {
	
	static Scanner sc = new Scanner(System.in);
	static UserService us = new UserServiceImpl();
	static AuthService as = new AuthServiceImpl();
	
	//Login menu logic, can either login, register a new account or exit the program
	public static void display() {
		String input; 
		
		do {
		System.out.println("Enter: \n1 to login\n2 to register\n3 to exit");
		input = sc.nextLine();
		String username;
		String password;
		
		switch(input) {
		case "1"://This is the login logic
			System.out.println("Enter username:");
			username = sc.nextLine();

			try {
				User user = us.getUser(username);
				System.out.println("Enter password:");
				password = sc.nextLine();
				User toBeChecked = new User(username, password);

				if(as.login(toBeChecked)) {
				System.out.println("Successfully logged in!");
				input = "3";
				} else {
					System.out.println("Wrong credentials");
				}
			} catch (UserNotFoundException e) {
				System.out.println("User was not found.");
			} catch (AuthException e) {
				System.out.println("Wrong credentials");
			}
			break;
		case "2"://This is the registration logic
			System.out.println("Enter a new username:");
			username = sc.nextLine();
			System.out.println("Enter a new password:");
			password = sc.nextLine();
			
			if(us.addUser(new User(username, password))) {
				System.out.println("Register successful!");
			}else {
				System.out.println("Unable to accomplish operation.");
			}
			break;
		case "3"://This is the exit logic
				System.out.println("Goodbye!");
				System.exit(0);
			break;
			default://The message that shows if the users input isn't one of the expected three
				System.out.println("Invalid input");
		}
		} while(!input.equals("3"));
	}
}
