package com.zero.controller;

import java.util.Scanner;
import com.zero.models.User;
import com.zero.services.AuthService;
import com.zero.services.AuthServiceImpl;
import com.zero.services.UserService;
import com.zero.services.UserServiceImpl;
import com.zero.exceptions.AuthException;
import com.zero.exceptions.UserNameTakenException;
import com.zero.exceptions.UserNotFoundException;


public class MenuScreen {
	
	static Scanner sc = new Scanner(System.in);
	static UserService us = new UserServiceImpl();
	static AuthService as = new AuthServiceImpl();
	
	//Login menu logic, can either login, register a new account or exit the program
	public static void display() {
		String input; 
		
		do {
		System.out.println("Enter: \n1. To login\n2. To register\n3. To exit\n");
		input = sc.nextLine();
		String username;
		String password;
		
		switch(input) {
		case "1"://This is the login logic
			System.out.println("Enter username: ");
			username = sc.nextLine();

			try {
				User user = us.getUserByName(username);
				System.out.println("Enter password: ");
				password = sc.nextLine();
				User toBeChecked = new User(1, username, password, 1);//Placeholder digits for now

				if(as.login(toBeChecked)) {
				System.out.println("Successfully logged in!\n");
				
				//Test pull up
				CustomerScreen.display(1);
				
				} else {
					System.out.println("Wrong credentials!\n");
				}
			} catch (UserNotFoundException e) {
				System.out.println("User was not found.\n");
			} catch (AuthException e) {
				System.out.println("Wrong credentials!\n");
			}
			break;
		case "2"://This is the registration logic
			System.out.println("Enter a new username: ");
			username = sc.nextLine();
			System.out.println("Enter a new password: ");
			password = sc.nextLine();
			
			us.addUser(new User(1, username, password, 1));
			break;
		case "3"://This is the exit logic
				System.out.println("Goodbye!\n");
				System.exit(0);
			break;
			default://The message that shows if the users input isn't one of the expected three
				System.out.println("Invalid input.\n");
		}
		} while(!input.equals("3"));
	}
}
