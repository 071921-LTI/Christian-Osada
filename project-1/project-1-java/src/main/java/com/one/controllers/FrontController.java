package com.one.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;
import com.one.services.UserServiceImpl;
import com.one.services.UserServices;

public class FrontController extends HttpServlet {

	private static Logger log = LogManager.getRootLogger();
	private static final long serialVersionUID = 1L;
	private RequestHelper rh = new RequestHelper();
	AuthService as = new AuthServiceImpl();
	UserServices us = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		addCorsHeader(rq.getRequestURI(),rs);
		
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");
		
		String tokenCheck = rq.getHeader("Authorization");
		System.out.println(tokenCheck);
		
		System.out.println(username + password);
		
		try {
			if(tokenCheck != null && username == null && password == null) {
				System.out.println(tokenCheck);
				log.info("User with token " + tokenCheck + " logged in");
				rs.setStatus(200);
				rh.process(rq, rs, tokenCheck);
			} else {

				User user = as.login(username, password);
				if (user != null) {
					//Creates token on successful login
					String tokenMake = user.getId() + ":" + user.getRole().getRole();
					rs.addHeader("Authorization", tokenMake);
					rs.setStatus(200);
					rh.process(rq, rs, tokenMake);
				}
			}
		} catch (UserNotFoundException e) {
			rs.sendError(404);
		}
		
		
	}
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doPut(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doDelete(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doOptions(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		addCorsHeader(rq.getRequestURI() ,rs);
		super.doOptions(rq, rs);
	}
	
	public static void addCorsHeader(String requestURI, HttpServletResponse rs) {
		rs.addHeader("Access-Control-Allow-Origin", "*");
		rs.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		rs.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
		rs.addHeader("Access-Control-Expose-Headers", "Content-Type, Accept, Authorization");
	}
}
