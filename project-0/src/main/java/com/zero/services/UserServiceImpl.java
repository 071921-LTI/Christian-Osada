package com.zero.services;

import java.util.List;

import com.zero.daos.UserCollection;
import com.zero.daos.UserDao;
import com.zero.exceptions.UserNameTakenException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public class UserServiceImpl implements UserService {

	private UserDao ud = new UserCollection();

	@Override
	public User getUserByName(String username) throws UserNotFoundException {
		return ud.getUserByName(username);
	}
	@Override
	public User getUserById(int id) throws UserNotFoundException {
		return ud.getUserById(id);
	}
	@Override
	public List<User> getUsers() {
		return ud.getUsers();
	}
	@Override
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	@Override
	public int deleteUser(int id) throws UserNotFoundException {
		return ud.deleteUser(id);
	}
}
