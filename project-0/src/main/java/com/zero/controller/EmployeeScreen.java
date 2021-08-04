package com.zero.controller;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.Item;
import com.zero.models.Offer;
import com.zero.models.User;

public class EmployeeScreen extends CustomerScreen {

	private static Logger log = LogManager.getRootLogger();

	//Driver method for all employee actions
	public static void display(int permssionLevel, int currentUserId) {
		int input;
		switch(permssionLevel) {
		case 2:
			do {
				shopDisplay();
				input = sc.nextInt();
				sc.nextLine();

				System.out.println("\n");
				switch(input) {
				case 1://Add item to shop
					addItemMethod(input, currentUserId);
					break;
				case 2://Remove item from shop
					removeItemMethod(input, currentUserId);
					break;
				case 3://Accept/Reject offer for an item
					offerMethod(input, currentUserId);
					break;
				case 4://Exit to login screen
					return;
				default:
					System.out.println("Invalid input.\n");
				}
			} while (true);
		case 3:
			do {
				shopDisplayManager();
				input = sc.nextInt();
				sc.nextLine();

				System.out.println("\n");
				switch(input) {
				case 1://Add item to shop
					addItemMethod(input, currentUserId);
					break;
				case 2://Remove item from shop
					removeItemMethod(input, currentUserId);
					break;
				case 3://Accept/Reject offer for an item
					offerMethod(input, currentUserId);
					break;
				case 4://User account methods
					accountMethods(input, currentUserId);
					break;
				case 5://Exit to login screen
					return;
				default:
					System.out.println("Invalid input.\n");
				}
			} while (true);
		}
	}

	//Displays the shop catalog and employee action choices
	public static void shopDisplay () {
		System.out.println("\n");

		System.out.println("All items in the shop:");
		Iterator<Item> iterator = itd.getItems().iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if (item.getOwnedBy() == 0) {
				System.out.println(item);
			}
		}
		System.out.println("Enter: \n1. To add an item to the shop \n2. "
				+ "To remove an item from the shop \n3. To view all offers and accept or reject them \n4. "
				+ "To go back to the login screen\n");
	}

	//Displays the shop catalog and employee action choices
	public static void shopDisplayManager () {
		System.out.println("\n");

		System.out.println("All items in the shop:");
		Iterator<Item> iterator = itd.getItems().iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if (item.getOwnedBy() == 0) {
				System.out.println(item);
			}
		}
		System.out.println("Enter: \n1. To add an item to the shop \n2. "
				+ "To remove an item from the shop \n3. To view all offers and accept or reject them \n4. To add or remove an employee account\n"
				+ "5. To go back to the login screen");
	}

	//Deals with adding items
	public static void addItemMethod (int input, int currentUserId) {
		System.out.println("Enter the name of the item you would like to add");
		String itemName = sc.nextLine();
		System.out.println("Enter the price of the item you would like to add");
		int itemPrice = sc.nextInt();
		sc.nextLine();
		Item newItem = new Item(0, itemName, itemPrice, 0);
		itd.addItem(newItem);
		try {
			log.info("Employee " + us.getUserById(currentUserId).getUsername() + " added item with Id " + itd.getItemByName(itemName).getItemId());
		} catch (UserNotFoundException e) {
			log.info("Log failed to find user account with Id " + currentUserId);
		} catch (ItemNotFoundException e) {
			log.info("Log failed to find item with name " + itemName);
		}
	}

	//Deals with removing items
	public static void removeItemMethod (int input, int currentUserId) {
		System.out.println("Enter the Id of the item you would like to remove");
		input = sc.nextInt();
		sc.nextLine();

		try {
			if (itd.getItemById(input).getOwnedBy() == 0) {
				itd.deleteItem(input);
				System.out.println("Item was successfully deleted.\n");
				log.info("Employee " + us.getUserById(currentUserId).getUsername() + " removed an item with the Id " + input);
			} else {
				System.out.println("Item not found.\n");
			}
		} catch (ItemNotFoundException e) {
			System.out.println("Item not found.");
		} catch (UserNotFoundException e) {
			log.info("Log failed to find user account with Id " + currentUserId);
		}
	}

	//Deals with everything to do with offers for employees
	public static void offerMethod (int input, int currentUserId) {
		do {
			System.out.println("\nAll of the current offers are: \n");
			Iterator<Offer> iterator2 = of.getOffers().iterator();
			while(iterator2.hasNext()) {
				Offer offer = iterator2.next();
				System.out.println(offer);
			}

			System.out.println("Enter: \n1. To accept an offer from the list \n2. "
					+ "To reject and offer from the list \n3. To go back\n");
			input = sc.nextInt();
			sc.nextLine();
			switch(input) {
			case 1://Accept an offer
				System.out.println("Enter the Offer Id of the offer you would like to accept.\n");
				input = sc.nextInt();
				sc.nextLine();
				try {
					if (of.getOfferById(input).getStatus() != "Accepted" || of.getOfferById(input).getStatus() != "Rejected") {
						of.updateOfferStatus(input, "Accepted");
						System.out.println("Offer was successfully accepted.\n");

						log.info("Employee " + us.getUserById(currentUserId).getUsername() + " accepted offer with Id " + input);

						iterator2 = of.getOffers().iterator();//Rejects all other offers made for the same item
						while(iterator2.hasNext()) {
							Offer offer = iterator2.next();
							if(offer.getItemIdOffer() == of.getOfferById(input).getItemIdOffer() && offer.getOfferId() != input) {
								of.updateOfferStatus(offer.getOfferId(), "Rejected");
							}
						}
					} else {
						System.out.println("Offer has already been accepted/rejected.\\n");
					}
				} catch (OfferNotFoundException e) {
					System.out.println("Offer not found.\n");
				} catch (UserNotFoundException e) {
					log.info("Log failed to find user account with Id " + currentUserId);
				}
				break;
			case 2://Reject an offer
				System.out.println("Enter the Id of the offer you would like to reject.\n");
				input = sc.nextInt();
				sc.nextLine();
				try {
					if (of.getOfferById(input).getStatus() != "Accepted" || of.getOfferById(input).getStatus() != "Rejected") {
						of.updateOfferStatus(input, "Rejected");
						System.out.println("Offer was successfully rejected.\n");
						log.info("Employee " + us.getUserById(currentUserId).getUsername() + " rejected offer with Id " + input);
					} else {
						System.out.println("Offer has already been accepted/rejected.\n");
					}
				} catch (OfferNotFoundException e) {
					System.out.println("Offer not found.\\n");
				} catch (UserNotFoundException e) {
					log.info("Log failed to find user account with Id " + currentUserId);
				}
				break;
			case 3://Go back to shop interaction screen
				//Just here to make sure 'Invalid Input' message isn't displayed.
				break;
			default:
				System.out.println("Invalid input.\n");
			}
		} while (input != 3);
	}

	public static void accountMethods (int input, int currentUserId) {
		do {
			System.out.println("\nAll of the current accounts are: \n");
			Iterator<User> iterator = us.getUsers().iterator();
			while(iterator.hasNext()) {
				User user = iterator.next();
				System.out.println(user);
			}

			System.out.println("Enter: \n1. To add an employee account \n2. "
					+ "To remove a user account \n3. To go back\n");
			input = sc.nextInt();
			sc.nextLine();
			switch(input) {
			case 1://Add an account
				addEmployeeMethod (input, currentUserId);
				break;
			case 2://Delete an account
				removeAccountMethod(input, currentUserId);
				break;
			case 3://Go back to shop interaction screen
				//Just here to make sure 'Invalid Input' message isn't displayed.
				break;
			default:
				System.out.println("Invalid input.\n");
			}
		} while (input != 3);
	}
	
	//Deals with adding employee accounts
	public static void addEmployeeMethod (int input, int currentUserId) {
		System.out.println("Enter the name of the employee account you would like to add:");
		String employeeName = sc.nextLine();
		System.out.println("Enter the password of the employee account you would like to add:");
		String employeePass = sc.nextLine();
		User newEmployee = new User(0, employeeName, employeePass, 2);
		us.addUser(newEmployee);
		try {
			log.info("Manager " + us.getUserById(currentUserId).getUsername() + " added employee account with Id " + us.getUserByName(employeeName).getUserId());
		} catch (UserNotFoundException e) {
			log.info("Log failed to find user account with Id " + currentUserId);
		}
	}
	
	//Deals with removing accounts
	public static void removeAccountMethod (int input, int currentUserId) {
		System.out.println("Enter the Id of the account you would like to remove:");
		input = sc.nextInt();
		sc.nextLine();

		try {
			us.deleteUser(input);
			System.out.println("Account was successfully deleted.\n");
			log.info("Manager " + us.getUserById(currentUserId).getUsername() + " removed account with the Id " + input);
		} catch (UserNotFoundException e) {
			log.info("Log failed to find user account with Id " + currentUserId);
		}
	}
}
