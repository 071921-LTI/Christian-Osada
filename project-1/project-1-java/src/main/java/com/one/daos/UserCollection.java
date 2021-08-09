package com.one.daos;

import java.util.List;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;

public class UserCollection implements UserDao {

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
}
