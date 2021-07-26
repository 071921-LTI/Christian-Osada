package com.zero.services;

import com.zero.daos.UserDao; 
import com.zero.daos.UserDatabase;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public class UserServiceImpl implements UserService {

	private UserDao ud = new UserDatabase();
	
	@Override
	public boolean addUser(User user) {
		return ud.addUser(user);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		return ud.getUser(username);
	}

}
