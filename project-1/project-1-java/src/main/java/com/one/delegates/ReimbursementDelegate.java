package com.one.delegates;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.daos.StatusDao;
import com.one.daos.StatusHibernate;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.exceptions.StatusNotFoundException;
import com.one.exceptions.TypeNotFoundException;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.Status;
import com.one.models.Type;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;
import com.one.services.ReimbursementService;
import com.one.services.ReimbursementServiceImpl;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;
import com.one.daos.TypeDao;
import com.one.daos.TypeHibernate;

public class ReimbursementDelegate implements Delegatable {
	
	private static Logger log = LogManager.getRootLogger();
	ReimbursementService rms = new ReimbursementServiceImpl();
	UserServices us = new UserServiceImpl();
	AuthService au = new AuthServiceImpl();
	StatusDao sd = new StatusHibernate();
	TypeDao td = new TypeHibernate();
	
	
	@Override
	public void process(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		// Retrieve GET, POST, PUT, DELETE...
		String method = rq.getMethod();

		switch (method) {
		case "GET":
			handleGet(rq, rs, token);
			break;
		case "POST":
			handlePost(rq, rs, token);
			break;
		case "PUT":
			handlePut(rq, rs, token);
			break;
		case "DELETE":
			handleDelete(rq, rs, token);
			break;
		default:
			rs.sendError(405);
		}

	}
	
	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handleGet");

		// String passed through the request if any
		String pathNext = (String) rq.getAttribute("pathNext");

		if (pathNext != null) {
			List<Reimbursement> reimbursements = null;
			try {
				reimbursements = rms.getReimbursementsbyAuthor(us.getUserById(Integer.valueOf(pathNext)));
			} catch (NumberFormatException | UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				log.info("User with token " + token + " queried the reimbursement database for" + reimbursements + ".");
			}

		} else {
			List<Reimbursement> reimbursements = rms.getReimbursements();
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				log.info("User with token " + token + " queried the reimbursement database for" + reimbursements + ".");
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePut");

		InputStream request = rq.getInputStream();
		Reimbursement statusChange = new ObjectMapper().readValue(request, Reimbursement.class);
		int id = statusChange.getId();
		
		System.out.println(id);
		System.out.println();
		Status statusBad = statusChange.getStatus();
		String stat = statusBad.getStatus();
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		
		try {
			Status statusGood = sd.getStatusByName(stat);
			Reimbursement reimbursement = rms.getReimbursementById(id);
			reimbursement.setStatus(statusGood);
			reimbursement.setResolver(au.getTokenUser(token));
			reimbursement.setResolved(timestamp);
			
			rms.updateReimbursement(reimbursement);
			rs.setStatus(200);
			log.info("User with token " + token + " changed reimbursement information to " + reimbursement 
					+ " in the reimbursement database.");
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StatusNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePost");

		
		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Reimbursement reimbursement = new ObjectMapper().readValue(request, Reimbursement.class);
		
		System.out.println(reimbursement.getId());
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		Type type = reimbursement.getType();
		System.out.println(type);
		
		try {
			reimbursement.setSubmitted(timestamp);
			reimbursement.setAuthor(au.getTokenUser(token));
			reimbursement.setStatus(sd.getStatusById(1));
			reimbursement.setType(td.getTypeByName(type.getType()));
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (StatusNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TypeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(reimbursement);
		
		if (!rms.addReimbursement(reimbursement)) {
			rs.sendError(400, "Unable to add user.");
		} else {
			rs.setStatus(201);
			log.info("User with token " + token + " added " + reimbursement + " to the reimbursement database.");
		}

	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Reimbursement reimbursement = new ObjectMapper().readValue(request, Reimbursement.class);
		
		try {
			if (!rms.deleteReimbursement(reimbursement)) {
				rs.sendError(400, "Unable to add user.");
			} else {
				rs.setStatus(201);
				log.info("User with token " + token + " deleted " + reimbursement + " from the reimbursement database.");
			}
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
