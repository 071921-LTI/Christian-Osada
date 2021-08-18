package com.one.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.one.daos.ReimbursementDao;
import com.one.daos.UserDao;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.Role;
import com.one.models.Status;
import com.one.models.Type;
import com.one.models.User;
import com.one.services.ReimbursementService;
import com.one.services.ReimbursementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {
	
	Date date = new Date();
	Timestamp timestamp = new Timestamp(date.getTime());
	
	Role empl = new Role(1, "Employee");
	Role mang = new Role(2, "Manager");
	User user = new User(1, "newUser", "password", "first", "last", "newuser@email.com", empl);
	Status status = new Status(2, "Accepted");
	Type type = new Type(4, "Travel");
	Reimbursement reimbursement = new Reimbursement(1, 200.00, "Hit a car", user, user, status, type);
	String token = "1:" + empl;
	String token2 = "2:" + mang;
	int tokenId = 1;
	int tokenId2 = 2;
	
	@Mock
	ReimbursementDao rmd;
	
	@InjectMocks
	ReimbursementService rms =  new ReimbursementServiceImpl();
	
	@Test
	public void findReimbursementById() {
		try {
			Mockito.when(rmd.getReimbursementById(1)).thenReturn(reimbursement);
			assertEquals(reimbursement, rms.getReimbursementById(1));
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList();
		reimbursements.add(reimbursement);
		Mockito.when(rmd.getReimbursements()).thenReturn(reimbursements);
		assertEquals(reimbursements, rms.getReimbursements());
	}
	
	@Test
	public void findReimbursementsByAuthor() {
		List<Reimbursement> reimbursements = new ArrayList();
		reimbursements.add(reimbursement);
		Mockito.when(rmd.getReimbursementsbyAuthor(user)).thenReturn(reimbursements);
		assertEquals(reimbursements, rms.getReimbursementsbyAuthor(user));
	}
	
	@Test
	public void addReimbursement() {
		Mockito.when(rmd.addReimbursement(reimbursement)).thenReturn(true);
		assertEquals(true, rms.addReimbursement(reimbursement));
	}
	@Test
	public void deleteReimbursement() {
		try {
			Mockito.when(rmd.deleteReimbursement(reimbursement)).thenReturn(true);
			assertEquals(true, rms.deleteReimbursement(reimbursement));
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void updateReimbursement() {
		try {
			Mockito.when(rmd.updateReimbursement(reimbursement)).thenReturn(true);
			assertEquals(true, rms.updateReimbursement(reimbursement));
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}