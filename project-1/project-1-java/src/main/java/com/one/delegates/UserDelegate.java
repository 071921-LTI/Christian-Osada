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
import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;

public class UserDelegate implements Delegatable{
	
	private static Logger log = LogManager.getRootLogger();
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
			List<User> users = us.getUsers();
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(users));
				log.info("User with token " + token + " queried " + users + " from the user database.");
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
