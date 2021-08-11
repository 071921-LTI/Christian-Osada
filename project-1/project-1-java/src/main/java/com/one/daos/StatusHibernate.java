package com.one.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.one.exceptions.StatusNotFoundException;
import com.one.models.Status;
import com.one.util.HibernateUtil;

public class StatusHibernate implements StatusDao{
	
	@Override
	public Status getStatusById(int id) throws StatusNotFoundException {
		Status s = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			s = ss.get(Status.class, id);
		}
		return s;
	}

	@Override
	public Status getStatusByName(String status) throws StatusNotFoundException {
		Status s = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			s = ss.get(Status.class, status);
		}
		return s;
	}
	
	@Override
	public Status addStatus(Status s) {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.save(s);
			tx.commit();
		}
		return s;
	}
}
