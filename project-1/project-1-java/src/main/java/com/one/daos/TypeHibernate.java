package com.one.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.one.exceptions.TypeNotFoundException;
import com.one.models.Type;
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
			t = ss.get(Type.class, type);
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
