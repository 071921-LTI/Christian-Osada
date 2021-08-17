package com.one.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.services.AuthService;
import com.one.services.AuthServiceImpl;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RequestHelper rh = new RequestHelper();
	AuthService as = new AuthServiceImpl();
	
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		addCorsHeader(rq.getRequestURI(),rs);
		
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");
		
		String token2 = rq.getHeader("Authorization");
		System.out.println(token2);
		
		System.out.println(username + password);
		
		try {
			if(token2 != null && username == null && password == null) {
				System.out.println(token2);
				rs.setStatus(200);
				rh.process(rq, rs, token2);
			} else {

				User user = as.login(username, password);
				if (user != null) {
					//Creates token on successful login
					String token = user.getId() + ":" + user.getRole().getRole();
					rs.addHeader("Authorization", token);
					rs.setStatus(200);
					rh.process(rq, rs, token);
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
