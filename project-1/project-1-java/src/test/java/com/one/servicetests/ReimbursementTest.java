package com.one.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import com.one.daos.ReimbursementDao;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;
import com.one.services.ReimbursementServiceImpl;
import com.one.util.ConnectionUtil;

@ExtendWith(MockitoExtension.class)
public class ReimbursementTest {

	@Mock
	private ReimbursementDao rd;
	
	@InjectMocks
	private ReimbursementServiceImpl rs;
	
	private static MockedStatic<ConnectionUtil> mockedConnectionUtil;
	private static Connection connection;
	
	Date date = new Date();
	Timestamp timestamp = new Timestamp(date.getTime());

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
	public void getByIdExists() throws ReimbursementNotFoundException {
		Reimbursement expected = new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1);
		Reimbursement actualResult = null;
		try {
			Mockito.when(rd.getReimbursementById(1)).thenReturn(new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
			actualResult = rs.getReimbursementById(1);
		} catch (ReimbursementNotFoundException e) {
		}
	assertEquals(expected, actualResult);
	}
	@Test
	public void getByIdNotExists() throws ReimbursementNotFoundException {
		Reimbursement expected = new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1);
		Reimbursement actualResult = null;
		try {
			Mockito.when(rd.getReimbursementById(1)).thenReturn(null);
			actualResult = rs.getReimbursementById(1);
		} catch (ReimbursementNotFoundException e) {
		}
	assertNotEquals(expected, actualResult);
	}
	
	@Test
	public void getExistantReimbursements() {
		List<Reimbursement> expectedReimbursements = new ArrayList<>();
		expectedReimbursements.add(new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		expectedReimbursements.add(new Reimbursement(2, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		
		List<Reimbursement> returnThis = new ArrayList<>();
		returnThis.add(new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		returnThis.add(new Reimbursement(2, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		
		List<Reimbursement> actualReimbursements = new ArrayList<>();
		Mockito.when(rd.getReimbursements()).thenReturn(returnThis);
		actualReimbursements = rs.getReimbursements();
		assertEquals(expectedReimbursements, actualReimbursements);
	}
	
	@Test
	public void getExistantReimbursementsByAuthor() {
		List<Reimbursement> expectedReimbursements = new ArrayList<>();
		expectedReimbursements.add(new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		expectedReimbursements.add(new Reimbursement(2, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		
		List<Reimbursement> returnThis = new ArrayList<>();
		returnThis.add(new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1));
		
		List<Reimbursement> actualReimbursements = new ArrayList<>();
		Mockito.when(rd.getReimbursementsbyAuthor(1)).thenReturn(returnThis);
		actualReimbursements = rs.getReimbursements();
		assertEquals(expectedReimbursements, actualReimbursements);
	}
	
	@Test
	public void getAddReimbursement() {
		boolean expected = true;
		Reimbursement reimbursement = new Reimbursement(1, 200, timestamp, timestamp, "description", 1, 1, 1, 1);
		Mockito.when(rd.addReimbursement(reimbursement)).thenReturn(true);
		boolean actualResult = rs.addReimbursement(reimbursement);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantReimbursement() {
		boolean expected = true;
		boolean actualResult = false;
		try {
			Mockito.when(rd.deleteReimbursement(1)).thenReturn(true);
			actualResult = rs.deleteReimbursement(1);
		} catch (ReimbursementNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantReimbursement() {
		boolean expected = false;
		boolean actualResult = true;
		try {
			Mockito.when(rd.deleteReimbursement(1)).thenReturn(false);
			actualResult = rs.deleteReimbursement(1);
		} catch (ReimbursementNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	
}
