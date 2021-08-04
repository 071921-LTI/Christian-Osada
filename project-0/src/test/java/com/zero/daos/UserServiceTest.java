package com.zero.daos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;
import com.zero.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserServiceImpl us;
	
	@Test
	public void getExistantUserByIdTest() {
		User expected = new User(1, "username1", "password1", 1);
		User actualResult = null;
			try {
				Mockito.when(ud.getUserById(1)).thenReturn(new User(1, "username1", "password1", 1));
				actualResult = us.getUserById(1);
			} catch (UserNotFoundException e) {
			}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantUserByIdTest() {
		User expected = new User(1, "username1", "password1", 1);
		User actualResult = null;
			try {
				Mockito.when(ud.getUserById(1)).thenReturn(new User(2, "username2", "password2", 2));
				actualResult = us.getUserById(1);
			} catch (UserNotFoundException e) {
			}
		assertNotEquals(expected, actualResult);
	}
	
	
	@Test
	public void getExistantUserByNameTest() {
		User expected = new User(1, "username1", "password1", 1);
		User actualResult = null;
			try {
				Mockito.when(ud.getUserByName("username1")).thenReturn(new User(1, "username1", "password1", 1));
				actualResult = us.getUserByName("username1");
			} catch (UserNotFoundException e) {
			}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantUserByNameTest() {
		User expected = new User(1, "username1", "password1", 1);
		User actualResult = null;
			try {
				Mockito.when(ud.getUserByName("username1")).thenReturn(new User(2, "username1", "password2", 2));
				actualResult = us.getUserByName("username1");
			} catch (UserNotFoundException e) {
			}
		assertNotEquals(expected, actualResult);
	}
	
	
	@Test
	public void getExistantItems() {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(new User(1, "username1", "password1", 1));
		expectedUsers.add(new User(2, "username2", "password2", 2));
		
		List<User> returnThis = new ArrayList<>();
		returnThis.add(new User(1, "username1", "password1", 1));
		returnThis.add(new User(2, "username2", "password2", 2));
		
		List<User> actualUsers = new ArrayList<>();
		Mockito.when(ud.getUsers()).thenReturn(returnThis);
		actualUsers = us.getUsers();
		assertEquals(expectedUsers, actualUsers);
	}
	@Test
	public void getNonExistantItems() {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(new User(1, "username1", "password1", 1));
		expectedUsers.add(new User(2, "username2", "password2", 2));
		
		List<User> returnThis = new ArrayList<>();
		returnThis.add(new User(1, "username1", "password1", 1));
		returnThis.add(new User(3, "username3", "password3", 3));
		
		List<User> actualUsers = new ArrayList<>();
		Mockito.when(ud.getUsers()).thenReturn(returnThis);
		actualUsers = us.getUsers();
		assertNotEquals(expectedUsers, actualUsers);
	}
	
	
	@Test
	public void getAddItem() {
		boolean expected = true;
		User user = new User(1, "username", "password", 1);
		Mockito.when(ud.addUser(user)).thenReturn(true);
		boolean actualResult = us.addUser(user);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantOffer() {
		int expected = -1;
		int actualResult = 0;
		try {
			Mockito.when(ud.deleteUser(1)).thenReturn(-1);
			actualResult = us.deleteUser(1);
		} catch (UserNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantOffer() {
		int expected = 0;
		int actualResult = -1;
		try {
			Mockito.when(ud.deleteUser(1)).thenReturn(0);
			actualResult = us.deleteUser(1);
		} catch (UserNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
}
