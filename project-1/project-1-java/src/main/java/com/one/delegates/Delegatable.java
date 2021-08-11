package com.one.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Delegatable {
	void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
	
	void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
	void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
	void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
	void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
	void handleOption(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException;
}
