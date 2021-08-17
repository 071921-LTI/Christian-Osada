package com.one.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.one.delegates.ReimbursementDelegate;
import com.one.delegates.UserDelegate;

public class RequestHelper {

	
	private UserDelegate ud = new UserDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	
	public void process(HttpServletRequest rq, HttpServletResponse rs, String token) throws IOException, ServletException {
		System.out.println("In requestHelper");
		String path = rq.getPathInfo();
		
		System.out.println(rq);
		
		if (path != null) {
			path = path.substring(1);
			
			if(path.indexOf("/") != -1) {
				String[] paths = path.split("/");
				path = paths[0];
				rq.setAttribute("pathNext", paths[1]);
			}
			
			switch(path) {
			case "users":
				ud.process(rq, rs, token);
				break;
			case "reimbursements":
				// Can add auth behavior here.
				System.out.println("In requestHelper2");
				rd.process(rq, rs, token);
				break;
			case "auth":
				// TODO: create auth delegate 
			default:
				rs.sendError(400, "Path not supported:" + path);
			}
		} else {
			rs.sendError(400, "No path found.");
		}
	}
}
