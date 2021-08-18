package com.one.delegates;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;
import com.one.services.ReimbursementService;
import com.one.services.ReimbursementServiceImpl;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;

public class ReimbursementDelegate implements Delegatable {
	
	private static Logger log = LogManager.getRootLogger();
	ReimbursementService rmd = new ReimbursementServiceImpl();
	UserServices us = new UserServiceImpl();
	AuthService au = new AuthServiceImpl();
	
	
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
				reimbursements = rmd.getReimbursementsbyAuthor(us.getUserById(Integer.valueOf(pathNext)));
			} catch (NumberFormatException | UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				log.info("User with token " + token + " queried the reimbursement database for" + reimbursements + ".");
			}

		} else {
			List<Reimbursement> reimbursements = rmd.getReimbursements();
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
				log.info("User with token " + token + " queried the reimbursement database for" + reimbursements + ".");
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePut");

	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePost");

		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Reimbursement reimbursement = new ObjectMapper().readValue(request, Reimbursement.class);
		System.out.println(token);
		try {
			reimbursement.setAuthor(au.getTokenUser(token));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!rmd.addReimbursement(reimbursement)) {
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
			if (!rmd.deleteReimbursement(reimbursement)) {
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
