package com.one.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.one.daos.UserDao;
import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.services.UserServiceImpl;
import com.one.util.ConnectionUtil;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserServiceImpl us;
	
	private static MockedStatic<ConnectionUtil> mockedConnectionUtil;
	private static Connection connection;

	/*
	 * Used to create a connection to our H2/ in memory db instead of "production"
	 * database
	 */
	public static Connection getH2Connection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@BeforeAll
	public static void init() throws SQLException {
		/*
		 * Mocking the ConnectionUtil class for the getConnectionFromEnv method to
		 * return a connection to the H2 while the mock is "open".
		 */
		mockedConnectionUtil = Mockito.mockStatic(ConnectionUtil.class);
		mockedConnectionUtil.when(ConnectionUtil::getConnectionFromEnv).then(I -> getH2Connection());
	}

	@AfterAll
	public static void end() {
			/*
			 * Drops h2 tables after tests.
			 */
			try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
				RunScript.execute(c, new FileReader("teardown.sql"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			/*
			 * Closing resource, mocked behavior ends.
			 */
			mockedConnectionUtil.close();
	}

	@BeforeEach
	public void setUp() {
			/*
			 * Clear previous tables and recreates tables before each tests
			 */
			try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
				RunScript.execute(c, new FileReader("setup.sql"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	@Test
	public void connectionTest() {
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			assertNotNull(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIdExists() throws UserNotFoundException {
		User expected = new User(1, "username", "password", "firstName", "lastName", "email", "role");
		User actualResult = null;
		try {
			Mockito.when(ud.getUserById(1)).thenReturn(new User(1, "username", "password", "firstName", "lastName", "email", "role"));
			actualResult = us.getUserById(1);
		} catch (UserNotFoundException e) {
		}
	assertEquals(expected, actualResult);
	}
	@Test
	public void getByIdNotExists() throws UserNotFoundException {
		User expected = new User(1, "username", "password", "firstName", "lastName", "email", "role");
		User actualResult = null;
		try {
			Mockito.when(ud.getUserById(1)).thenReturn(null);
			actualResult = us.getUserById(1);
		} catch (UserNotFoundException e) {
		}
	assertNotEquals(expected, actualResult);
	}
	
	@Test
	public void getByUsernameExists() throws UserNotFoundException {
		User expected = new User(1, "username", "password", "firstName", "lastName", "email", "role");
		User actualResult = null;
		try {
			Mockito.when(ud.getUserByUsername("username")).thenReturn(new User(1, "username", "password", "firstName", "lastName", "email", "role"));
			actualResult = us.getUserByUsername("username");
		} catch (UserNotFoundException e) {
		}
	assertEquals(expected, actualResult);
	}
	@Test
	public void getByUsernameNotExists() throws UserNotFoundException {
		User expected = new User(1, "username", "password", "firstName", "lastName", "email", "role");
		User actualResult = null;
		try {
			Mockito.when(ud.getUserByUsername("username")).thenReturn(null);
			actualResult = us.getUserByUsername("username");
		} catch (UserNotFoundException e) {
		}
	assertNotEquals(expected, actualResult);
	}
	
	@Test
	public void getExistantUsers() {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(new User(1, "username", "password", "firstName", "lastName", "email", "role"));
		expectedUsers.add(new User(2, "username2", "password2", "firstName2", "lastName2", "email2", "role2"));
		
		List<User> returnThis = new ArrayList<>();
		returnThis.add(new User(1, "username", "password", "firstName", "lastName", "email", "role"));
		returnThis.add(new User(2, "username2", "password2", "firstName2", "lastName2", "email2", "role2"));
		
		List<User> actualUsers = new ArrayList<>();
		Mockito.when(ud.getUsers()).thenReturn(returnThis);
		actualUsers = us.getUsers();
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Test
	public void getAddUser() {
		boolean expected = true;
		User user = new User(1, "username", "password", "firstName", "lastName", "email", "role");
		Mockito.when(ud.addUser(user)).thenReturn(true);
		boolean actualResult = us.addUser(user);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantUser() {
		boolean expected = true;
		boolean actualResult = false;
		try {
			Mockito.when(ud.deleteUser(1)).thenReturn(true);
			actualResult = us.deleteUser(1);
		} catch (UserNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantUser() {
		boolean expected = false;
		boolean actualResult = true;
		try {
			Mockito.when(ud.deleteUser(1)).thenReturn(false);
			actualResult = us.deleteUser(1);
		} catch (UserNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	
}
