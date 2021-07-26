package com.zero.services;

import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public interface UserService {

	//Checks if inputed user exists
	public abstract boolean addUser(User user);
	public abstract User getUser(String username) throws UserNotFoundException;
	
}
