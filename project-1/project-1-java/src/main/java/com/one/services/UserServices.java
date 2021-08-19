package com.one.services;

import java.util.List;

import com.one.exceptions.UserNotFoundException;
import com.one.models.Role;
import com.one.models.User;

public interface UserServices {
	
	//Checks if inputed user exists
	public abstract User getUserById(int id) throws UserNotFoundException;
	public abstract User getUserByUsername(String username) throws UserNotFoundException;
	public abstract List<User> getUsers();
	public abstract List<User> getUsersByRole(Role role);
	public abstract boolean addUser(User user);
	public abstract boolean deleteUser(User user) throws UserNotFoundException;
	public abstract boolean updateUser(User user) throws UserNotFoundException;
}
