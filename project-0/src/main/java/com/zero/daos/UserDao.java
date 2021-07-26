package com.zero.daos;

import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public interface UserDao {
	public abstract User getUser(String username) throws UserNotFoundException;
	public abstract boolean addUser(User user);
}
