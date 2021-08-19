package com.one.services;

import java.util.List;

import com.one.daos.UserDao;
import com.one.daos.UserHibernate;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Role;
import com.one.models.User;

public class UserServiceImpl implements UserServices{
	
	private UserDao ud = new UserHibernate();

	public User getUserById(int id) throws UserNotFoundException {
		return ud.getUserById(id);
	}
	public User getUserByUsername(String username) throws UserNotFoundException {
		return ud.getUserByUsername(username);
	}
	public List<User> getUsers() {
		return ud.getUsers();
	}
	@Override
	public List<User> getUsersByRole(Role role) {
		return ud.getUsersByRole(role);
	}
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	public boolean deleteUser(User user) throws UserNotFoundException {
		return ud.deleteUser(user);
	}
	public boolean updateUser(User user) throws UserNotFoundException {
		return ud.updateUser(user);
	}

}
