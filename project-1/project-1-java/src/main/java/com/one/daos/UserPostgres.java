package com.one.daos;

import java.util.List;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;

public class UserPostgres implements UserDao{

	public User getUserById(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByUsername(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUser(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
