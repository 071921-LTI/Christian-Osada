package com.zero.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	private static Connection con;
	public static Connection getConnectionFromEnv() throws SQLException {
		
		String url = System.getenv("postgreURL");
		String username = System.getenv("postgreUsername");
		String password = System.getenv("postgrePassword");
		
		if(con == null || con.isClosed()) {
			con = DriverManager.getConnection(url, username, password);
		} 
		
		return con;
	}
}
