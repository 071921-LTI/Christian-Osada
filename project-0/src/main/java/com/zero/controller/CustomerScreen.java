package com.zero.controller;

import java.util.Iterator;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Item;
import com.zero.models.Offer;
import com.zero.services.ItemService;
import com.zero.services.ItemServiceImpl;
import com.zero.services.OfferService;
import com.zero.services.OfferServiceImpl;

public class CustomerScreen extends MenuScreen{
	
	static ItemService itd = new ItemServiceImpl();
	static OfferService of = new OfferServiceImpl();

	//Where users interact/see items in the shop
	public static void display(int permssionLevel, int currentUserId) {

		String input;
		switch(permssionLevel) {
		case 1://Customer menu
			do {
				System.out.println("Items available in shop:");
				Iterator<Item> iterator = itd.getItems().iterator();
				while(iterator.hasNext()) {
				    Item item = iterator.next();
				    if (item.getOwnedBy() == 0) {
				    	System.out.println(item);
				    }
				}
				
				System.out.println("\nEnter: \n1. To to make an offer for an item \n2. "
						+ "To view the status of your current offers \n3. To view a list of owned items \n4. "
						+ "To go back to the login screen\n");
				input = sc.nextLine();
				switch(input) {
				case "1"://Make an offer for an item
					System.out.println("Please enter the ID of the item you would like to make an offer for.\n");
					input = sc.nextLine();
					int intInput = Integer.parseInt(input);
					
					try {
						Item item = itd.getItemById(intInput);
						if (item.getOwnedBy() == 0) {
							Offer offer = new Offer (0, currentUserId, intInput, "N/A");
							of.addOffer(offer);
							System.out.println("Offer successfully made.\n");
						} else {
							System.out.println("Invalid item ID.\n");
						}
					} catch (ItemNotFoundException e) {
						System.out.println("Invalid item ID.\n");
					}
					break;
				case "2"://View status of current offers
					System.out.println("\nYour current offers are: ");
					Iterator<Offer> iterator2 = of.getOffers().iterator();
					while(iterator2.hasNext()) {
						Offer offer = iterator2.next();
					    if (offer.getOffererId() == currentUserId) {
					    	System.out.println(offer);
					    }
					}
					System.out.println("\n");

					break;
				case "3"://View items you own
					System.out.println("The items you own are:");
					iterator = itd.getItems().iterator();
					while(iterator.hasNext()) {
					    Item item = iterator.next();
					    if (item.getOwnedBy() == currentUserId) {
					    	System.out.println(item);
					    }
					}
					System.out.println("\n");
					break;
				case "4"://Exit menu
					System.out.println("Goodbye.\n");
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
