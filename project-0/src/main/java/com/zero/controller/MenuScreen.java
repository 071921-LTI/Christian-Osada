package com.zero.controller;

import java.util.Scanner;

public class MenuScreen {
	static Scanner sc = new Scanner(System.in);
	
	// Menu logic
	public static void display() {
		String input;
		do {
			System.out.println("Enter: \n1. To login\n2. To register\n3. To exit");
			input = sc.nextLine();
			
			switch(input) {
			case "1":

				break;
			case "2":

				break;
			case "3":
					System.out.println("Goodbye!");
				break;
				default:
					System.out.println("Invalid input");
			}
			} while(!input.equals("3"));
	}
}
