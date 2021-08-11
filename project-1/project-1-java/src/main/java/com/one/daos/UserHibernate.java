package com.one.daos;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.one.exceptions.UserNotFoundException;
import com.one.models.User;
import com.one.util.HibernateUtil;

public class UserHibernate implements UserDao{

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		User u = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			u = ss.get(User.class, id);
		}
		return u;
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		User u = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
	        Query query = ss.createQuery("FROM User WHERE username = :username");
	        query.setParameter("username", username);
	        List list = query.list();
	        u = (User) list.get(0);
		}
		return u;
	}

	@Override
	public List<User> getUsers() {
		List<User> u = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			u = ss.createQuery("FROM Trainer", User.class).list();
		}
		return u;
	}

	@Override
	public boolean addUser(User u) {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.save(u);
			tx.commit();
		}
		return true;
	}

	@Override
	public boolean deleteUser(User u) throws UserNotFoundException {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.delete(u);
			tx.commit();
		}
		return true;
	}

	@Override
	public boolean updateUser(User u) throws UserNotFoundException {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.update(u);
			tx.commit();
		}
		return true;
	}

}
