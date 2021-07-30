package com.zero.services;

import java.util.List;
import java.util.ArrayList;
import com.zero.exceptions.UserNameTakenException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public interface UserService {

	//Checks if inputed user exists
	public abstract User getUserByName(String username) throws UserNotFoundException;
	public abstract User getUserById(int id) throws UserNotFoundException;
	public abstract List<User> getUsers();
	public abstract boolean addUser(User user);
	public abstract int deleteUser(int id) throws UserNotFoundException;
}
