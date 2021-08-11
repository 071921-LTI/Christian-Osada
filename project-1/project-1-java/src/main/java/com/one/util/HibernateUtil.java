package com.one.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() {
		
		if(sf == null || sf.isClosed() == true) {
			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.url", System.getenv("postgreURL"));
			cfg.setProperty("hibernate.connection.username", System.getenv("postgreUsername"));
			cfg.setProperty("hibernate.connection.password", System.getenv("postgrePassword"));
			
			sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sf;
	}
}
