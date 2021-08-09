package com.one.services;

import java.util.List;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.daos.UserCollection;
import com.one.daos.UserDao;



public class UserServiceImpl implements UserServices{
	
	private UserDao ud = new UserCollection();

	public User getUserById(int id) throws UserNotFoundException {
		return ud.getUserById(id);
	}
	public User getUserByUsername(String username) throws UserNotFoundException {
		return ud.getUserByUsername(username);
	}
	public List<User> getUsers() {
		return ud.getUsers();
	}
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	public boolean deleteUser(int id) throws UserNotFoundException {
		return ud.deleteUser(id);
	}
	public boolean updateUser(User user) throws UserNotFoundException {
		return ud.updateUser(user);
	}
}
