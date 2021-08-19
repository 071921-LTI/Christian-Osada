package com.one.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.one.exceptions.StatusNotFoundException;
import com.one.models.Status;
import com.one.models.Type;
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
	        Query query = ss.createQuery("FROM Status WHERE status = :status");
	        query.setParameter("status", status);
	        List list = query.list();
	        s = (Status) list.get(0);
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
