package com.one.services;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;

public interface AuthService {
	User login(String username, String password) throws UserNotFoundException;
	boolean authorize(String token) throws UserNotFoundException;
}
