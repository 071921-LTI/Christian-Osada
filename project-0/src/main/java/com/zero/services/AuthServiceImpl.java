package com.zero.services;

import com.zero.daos.UserDao;
import com.zero.daos.UserPostgres;
import com.zero.exceptions.AuthException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public class AuthServiceImpl implements AuthService{
	
	private UserDao ud = new UserPostgres();
	
	@Override
	public boolean login(User user) throws AuthException {
		try {
			User persistedUser = ud.getUserByName(user.getUsername());
			if(persistedUser.getPassword().equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (UserNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
