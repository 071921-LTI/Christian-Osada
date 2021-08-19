package com.one.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.one.exceptions.TypeNotFoundException;
import com.one.models.Type;
import com.one.models.User;
import com.one.util.HibernateUtil;

public class TypeHibernate implements TypeDao{

	@Override
	public Type getTypeById(int id) throws TypeNotFoundException {
		Type t = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			t = ss.get(Type.class, id);
		}
		return t;
	}

	@Override
	public Type getTypeByName(String type) throws TypeNotFoundException {
		Type t = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
	        Query query = ss.createQuery("FROM Type WHERE type = :type");
	        query.setParameter("type", type);
	        List list = query.list();
	        t = (Type) list.get(0);
		}
		return t;
	}	
	
	@Override
	public Type addType(Type t) {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.save(t);
			tx.commit();
		}
		return t;
	}
}
