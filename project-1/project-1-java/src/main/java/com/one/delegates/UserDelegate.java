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
import com.one.daos.RoleDao;
import com.one.daos.RoleHibernate;
import com.one.exceptions.RoleNotFoundException;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;

public class UserDelegate implements Delegatable{
	
	final static Logger log = LogManager.getLogger(UserServices.class);
	
	UserServices us = new UserServiceImpl();
	AuthService au = new AuthServiceImpl();
	RoleDao rd = new RoleHibernate();

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
			try {
				User user = us.getUserById(Integer.valueOf(pathNext));
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(user));
					log.info("User with token " + token + " queried " + user + " from the user database.");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				rs.sendError(404);
			}
		} else {
			List<User> users = null;
			try {
				users = us.getUsersByRole(rd.getRoleById(1));
			} catch (RoleNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(users));
				log.info("User with token " + token + " queried " + users + " from the user database.");
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePut");
		
		InputStream request = rq.getInputStream();
		User user = new ObjectMapper().readValue(request, User.class);
		
		try {
			User oldUser = au.getTokenUser(token);
			user.setUsername(oldUser.getUsername());
			user.setPassword(oldUser.getPassword());
			user.setRole(oldUser.getRole());
			
			us.updateUser(user);
			rs.setStatus(200);
			log.info("User with token " + token + " changed their information to " + user + " in the user database.");
		} catch (UserNotFoundException e) {
			rs.sendError(400, "Unable to add user.");
			e.printStackTrace();
		}
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handlePost");
//		InputStream request = rq.getInputStream();
//			// Converts the request body into a User.class object
//			User user = new ObjectMapper().readValue(request, User.class);
//		
//			if (!us.addUser(user)) {
//				rs.sendError(400, "Unable to add user.");
//			} else {
//				rs.setStatus(201);
//				log.info("User with token " + token + " added " + user + " to the user database.");
//			}
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs, String token) throws ServletException, IOException {
		System.out.println("In handleDelete");

		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		User user = new ObjectMapper().readValue(request, User.class);
		
		try {
			if (!us.deleteUser(user)) {
				rs.sendError(400, "Unable to add user.");
			} else {
				rs.setStatus(201);
				log.info("User with token " + token + " deleted " + user + " from the user database.");
			}
		} catch (UserNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
