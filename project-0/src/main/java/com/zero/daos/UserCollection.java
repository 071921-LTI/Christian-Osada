package com.zero.daos;

import com.zero.daos.UserDao;

import java.util.ArrayList;
import java.util.List;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public class UserCollection implements UserDao {

	private static List<User> users;
	private UserDao ud = new UserPostgres();
	
	public UserCollection(){
		users = new ArrayList<>();
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException {
		User user = ud.getUserByName(name);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		User user = ud.getUserById(id);
		if(id == user.getUserId()) {
			return user;
		}
		throw new UserNotFoundException();
	}

	@Override
	public List<User> getUsers() {
		users = ud.getUsers();
		return users;
	}

	@Override
	public boolean addUser(User user){
			return ud.addUser(user);
	}

	@Override
	public int deleteUser(int id) throws UserNotFoundException {
		if(ud.getUserById(id).getUserId() == id) {
			ud.deleteUser(id);
			return deleteUser(id);
		}
		throw new UserNotFoundException();
	}
}
