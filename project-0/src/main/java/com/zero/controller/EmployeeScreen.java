package com.zero.controller;

import java.util.Iterator;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Item;
import com.zero.models.Offer;

public class EmployeeScreen extends CustomerScreen {
	//Driver method for all employee actions
	public static void display() {
		int input;
		do {
			shopDisplay();
			input = sc.nextInt();
			sc.nextLine();
			
			System.out.println("\n");
			switch(input) {
			case 1://Add item to shop
				addItemMethod(input);
				break;
			case 2://Remove item from shop
				removeItemMethod(input);
				break;
			case 3://Accept/Reject offer for an item
				offerMethod(input);
				break;
			case 4://Exit to login screen
				return;
			default:
				System.out.println("Invalid input.\n");
			}
		} while (true);
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
	
	//Deals with adding items
	public static void addItemMethod (int input) {
		System.out.println("Enter the name of the item you would like to add");
		String itemName = sc.nextLine();
		System.out.println("Enter the price of the item you would like to add");
		int itemPrice = sc.nextInt();
		sc.nextLine();
		Item newItem = new Item(0, itemName, itemPrice, 0);
		itd.addItem(newItem);
	}
	
	//Deals with removing items
	public static void removeItemMethod (int input) {
		System.out.println("Enter the Id of the item you would like to remove");
		input = sc.nextInt();
		sc.nextLine();
		
		try {
			if (itd.getItemById(input).getOwnedBy() == 0) {
				itd.deleteItem(input);
				System.out.println("Item was successfully deleted.\n");
			} else {
				System.out.println("Item not found.\n");
			}
		} catch (ItemNotFoundException e) {
			System.out.println("Item not found.");
		}
	}
	
	//Deals with everything to do with offers for employees
	public static void offerMethod (int input) {
		do {
			System.out.println("\nAll of the current offers are: \n");
			Iterator<Offer> iterator2 = of.getOffers().iterator();
			while(iterator2.hasNext()) {
				Offer offer = iterator2.next();
				if(!offer.getStatus().equals("Accepted") && !offer.getStatus().equals("Rejected")) {
					System.out.println(offer);
				}
			}

			System.out.println("Enter: \n1. To accept an offer from the list \n2. "
					+ "To reject and offer from the list \n3. To return to the shop menu\n");
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
					} else {
						System.out.println("Offer has already been accepted/rejected.\n");
					}
				} catch (OfferNotFoundException e) {
					System.out.println("Offer not found.\\n");
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
}
