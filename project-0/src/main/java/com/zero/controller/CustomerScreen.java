package com.zero.controller;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.Item;
import com.zero.models.Offer;
import com.zero.services.ItemService;
import com.zero.services.ItemServiceImpl;
import com.zero.services.OfferService;
import com.zero.services.OfferServiceImpl;

public class CustomerScreen extends MenuScreen{
	
	private static Logger log = LogManager.getRootLogger();
	static ItemService itd = new ItemServiceImpl();
	static OfferService of = new OfferServiceImpl();

	//Driver method for all customer actions
	public static void display(int permssionLevel, int currentUserId) {

		int input;
		switch(permssionLevel) {
		case 1://Customer menu
			do {
				initialScreenDisplay();
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1://Make an offer for an item
					makeAnOffer(currentUserId, input);
					break;
				case 2://View status of current offers and pay for accepted ones
					viewOffersAndPay(currentUserId, input);
					break;
				case 3://View items you own
					viewOwnedItems(currentUserId, input);
					break;
				case 4://Exit menu
					System.out.println("Goodbye.\n");
					return;
				default:
					System.out.println("Invalid input.\n");
				}
			} while (true);
		case 2://Employee menu
			EmployeeScreen.display(permssionLevel, currentUserId);
			return;
		case 3://Manager menu
			EmployeeScreen.display(permssionLevel, currentUserId);
			return;
		}
	}
	
	//Displays the shop catalog and customer action choices
	public static void initialScreenDisplay () {
		System.out.println("All items in the shop:");
		Iterator<Item> iterator = itd.getItems().iterator();
		while(iterator.hasNext()) {
		    Item item = iterator.next();
		    if (item.getOwnedBy() == 0) {
		    	System.out.println(item);
		    }
		}
		
		System.out.println("\nEnter: \n1. To make an offer for an item \n2. "
				+ "To view the status of your current offers and pay for accepted ones\n3. "
				+ "To view owned items\n4. To return to the login screen \n");
	}
	
	//Deals with making offers for items
	public static void makeAnOffer (int currentUserId, int input) {
		System.out.println("Please enter the ID of the item you would like to make an offer for.\n");
		input = sc.nextInt();
		sc.nextLine();
		
		try {
			Item item = itd.getItemById(input);
			if (item.getOwnedBy() == 0) {
				Offer offer = new Offer (0, currentUserId, input, "N/A");
				of.addOffer(offer);
				System.out.println("Offer successfully made.\n");
				log.info("Customer " + us.getUserById(currentUserId).getUsername() + " made an offer for item with Id " + offer.getItemIdOffer());
			} else {
				System.out.println("Invalid item ID.\n");
			}
		} catch (ItemNotFoundException e) {
			System.out.println("Invalid item ID.\n");
		} catch (UserNotFoundException e) {
			log.info("Log failed to find user account with Id " + currentUserId);
		}
	}
	
	//Deals with viewing current offers and paying for accepted ones
	public static void viewOffersAndPay (int currentUserId, int input) {
		do {
			System.out.println("\nYour current offers are: ");
			Iterator<Offer> iterator2 = of.getOffers().iterator();
			while(iterator2.hasNext()) {
				Offer offer = iterator2.next();
				if (offer.getOffererId() == currentUserId) {
					System.out.println(offer);
				}
			}

			System.out.println("\nEnter: \n1. To pay for an accepted offer\n2. "
					+ "To go back to the shop screen \n");
			input = sc.nextInt();
			sc.nextLine();
			switch(input) {
			case 1:// To pay for an accepted offer
				System.out.println("\nEnter the Id of the offer you would like to pay for\n");
				input = sc.nextInt();
				sc.nextLine();
				
				try {//If chosen offer is status is 'Accepted', update owner Id of the offers associated item to the current users and delete the offer
					if (of.getOfferById(input).getOffererId() == currentUserId && of.getOfferById(input).getStatus().equals("Accepted")) {
						itd.updateItemOwner(of.getOfferById(input).getItemIdOffer(), currentUserId);
						log.info("Customer " + us.getUserById(currentUserId).getUsername() + " now owns the item that has Id " + of.getOfferById(input).getItemIdOffer());
						of.deleteOffer(input);
						System.out.println("\nItem successfully paid for, you are now the owner.\n");
					} else {
						System.out.println("Offer/Item not found.");
					}
				} catch (OfferNotFoundException | ItemNotFoundException e) {
					System.out.println("Offer/Item not found.");
				} catch (UserNotFoundException e) {
					log.info("Log failed to find user account with Id " + currentUserId);
				}
				break;
			case 2://To go back to the shop screen
				//This is just here to stop the 'Invalid Input' message from displaying
				break;
			default:
				System.out.println("Invalid input.");
			}
		} while (input != 2);
	}
	
	//Displays users currently owned item (items that have their Id in the ownedBy/owned_by  field/column)
	public static void viewOwnedItems (int currentUserId, int input) {
		System.out.println("The items you own are:");
		Iterator<Item> iterator = itd.getItems().iterator();
		while(iterator.hasNext()) {
		    Item item = iterator.next();
		    if (item.getOwnedBy() == currentUserId) {
		    	System.out.println(item);
		    }
		}
		System.out.println("\n");
	}
}
