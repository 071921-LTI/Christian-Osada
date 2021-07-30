package com.zero.controller;

public class CustomerScreen extends MenuScreen{

	//Where users interact/see items in the shop
	public static void display(int permssionLevel) {

		String input;
		switch(permssionLevel) {
		case 1://Customer menu
			do {
				System.out.println("Items in shop:\n");
				//TODO: Some sort of loop/iterable to print out all AVAILABLE items and their details from the shop list
				System.out.println("Enter: \n1. To to make an offer for an item \n2. "
						+ "To view the status of already made offers \n3. To view a list of owned items \n4. "
						+ "To go back to the login screen\n");
				input = sc.nextLine();
				switch(input) {
				case "1"://Make an offer for an item
					System.out.println("Please enter the ID of the item you would like to make an offer for.");
					input = sc.nextLine();
					//TODO: Something that checks given Id against item list. If item exists extend offer,
					//otherwise tell user input is invalid. 

					break;
				case "2"://View status of offers for items
					//TODO: Shows list of items you have extended offers for and the offer status

					break;
				case "3"://View items you own
					//TODO: Shows list of items you own

					break;
				case "4"://Exit menu
					//TODO: Exit menu
					return;
				default:
					System.out.println("Invalid input.\n");
				}
			} while (true);
		case 2://Employee menu
			EmployeeScreen.display();
			return;
		}
	}
}
