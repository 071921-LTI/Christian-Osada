package com.zero;

import com.zero.controller.MenuScreen;
import com.zero.daos.ItemDao;
import com.zero.daos.ItemPostgres;
import com.zero.daos.OfferDao;
import com.zero.daos.OfferPostgres;
import com.zero.daos.UserDao;
import com.zero.daos.UserPostgres;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.Offer;
import com.zero.models.User;

public class Driver {
	public static void main(String[] args) {
		MenuScreen.display();
//		UserDao ud = new UserPostgres();
//		OfferDao od = new OfferPostgres();
//		ItemDao id = new ItemPostgres();
////		User user = new User(0, "delete", "delete", 1);
////		System.out.println(id.updateItemOwner(2, 2));
//		Offer offer = new Offer(0, 2, 2, "N/A");
//		
//		
//		od.updateOfferStatus(5, "Accepted");
//		try {
//			System.out.println(ud.usernameExist("username1"));
//		} catch (UserNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
