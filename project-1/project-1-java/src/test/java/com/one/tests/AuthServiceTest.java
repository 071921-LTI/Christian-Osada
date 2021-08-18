package com.one.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	

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
	AuthService as =  new AuthServiceImpl();
	
	@Test
	public void loginSuccess() {
		try {
			Mockito.when(ud.getUserByUsername("newUser")).thenReturn(user);
			assertEquals(user, as.login("newUser", "password"));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		@Test
		public void loginFailure() {
			try {
				Mockito.when(ud.getUserByUsername("newUser")).thenReturn(user2);
				assertEquals(null, as.login("newUser", "password"));
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		@Test
		public void getTokenUserSuccess() {
			try {
				Mockito.when(ud.getUserById(tokenId)).thenReturn(user);
				assertEquals(user, as.getTokenUser(token));
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
//		@Test
//		public void authorizeSucces() {
//			try {
//				Mockito.when(ud.getUserById(tokenId)).thenReturn(user);
//				assertEquals(true, as.authorize(token));
//			} catch (UserNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//		@Test
//		public void authorizeFailure() {
//			try {
//				Mockito.when(ud.getUserById(tokenId2)).thenReturn(user2);
//				assertEquals(false, as.authorize(token));
//			} catch (UserNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
}