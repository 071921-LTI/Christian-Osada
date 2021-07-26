package com.zero.services;

import com.zero.exceptions.AuthException;
import com.zero.models.User;

public interface AuthService {

	public abstract boolean login(User user) throws AuthException;
}
