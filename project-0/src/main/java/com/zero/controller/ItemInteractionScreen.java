package com.zero.controller;

public class ItemInteractionScreen extends MenuScreen{

	//Where users interact/see items in the shop
	public static void display(String permssionLevel) {

		String input;
		switch(permssionLevel) {
		case "1"://Customer menu
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
		case "2"://Employee menu
			do {
				System.out.println("Items in shop:\n");
				//TODO: Some sort of loop/iterable to print out ALL items and their details from the shop list
				System.out.println("Enter: \n1. To add an item to the shop \n2. "
						+ "To remove an item from the shop \n3. To accept or reject an offer for an item \n4. "
						+ "To go back to the login screen\n");
				input = sc.nextLine();
				switch(input) {
				case "1"://Add item to shop
					//TODO: Method to add items to shop

					break;
				case "2"://Remove item from shop
					//TODO: Method to remove items from shop

					break;
				case "3"://Accept/Reject offer for an item
					
					
					do {
						//TODO: Method to show list of current offers that aren't accepted/rejected
						System.out.println("Enter: \n1. To accept an offer from the list \n2. "
								+ "To reject and offer fro mthe list \n3. To return to the shop menu\n");
						input = sc.nextLine();
						switch(input) {
						case "1"://Accept offer
							//TODO: Method to accepts offers, removing the item from shop and placing it in owners
							//'owned' list.

							break;
						case "2"://Reject offer
							//TODO: Method to reject offer

							break;
						case "3"://Go back to shop interaction screen
							break;
						default:
							System.out.println("Invalid input.\n");
						}
						break;
					}while (!input.equals("3"));
				case "4"://Exit to login screen
					return;
				default:
					System.out.println("Invalid input.\n");
				}
			} while (true);
		}
	}
}
