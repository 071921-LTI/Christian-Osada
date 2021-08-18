package com.one.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.one.daos.UserDao;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Role;
import com.one.models.User;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	Role empl = new Role(1, "Employee");
	Role mang = new Role(2, "Manager");
	User user = new User(1, "newUser", "password", "first", "last", "newuser@email.com", empl);
	User user2 = new User(2, "newUser2", "password2", "first2", "last2", "newuser2@email.com", mang);
	String token = "1:" + empl;
	String token2 = "2:" + mang;
	int tokenId = 1;
	int tokenId2 = 2;
	
	@Mock
	UserDao ud;
	
	@InjectMocks
	UserServices us =  new UserServiceImpl();
	
	@Test
	public void findUserById() {
		try {
			Mockito.when(ud.getUserById(1)).thenReturn(user);
			assertEquals(user, us.getUserById(1));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findUserbyUsername() {
		try {
			Mockito.when(ud.getUserByUsername("newUser")).thenReturn(user);
			assertEquals(user, us.getUserByUsername("newUser"));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findUsers() {
		List<User> users = new ArrayList();
		users.add(user);
		users.add(user2);
		Mockito.when(ud.getUsers()).thenReturn(users);
		assertEquals(users, us.getUsers());
	}
	
	@Test
	public void addUser() {
		Mockito.when(ud.addUser(user)).thenReturn(true);
		assertEquals(true, us.addUser(user));
	}
	@Test
	public void deleteUser() {
		try {
			Mockito.when(ud.deleteUser(user)).thenReturn(true);
			assertEquals(true, us.deleteUser(user));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void updateUser() {
		try {
			Mockito.when(ud.updateUser(user)).thenReturn(true);
			assertEquals(true, us.updateUser(user));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
