package com.one.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.one.exceptions.RoleNotFoundException;
import com.one.models.Role;
import com.one.util.HibernateUtil;

public class RoleHibernate implements RoleDao {

	@Override
	public Role getRoleById(int id) throws RoleNotFoundException {
		Role r = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			r = ss.get(Role.class, id);
		}
		return r;
	}

	@Override
	public Role getRoleByName(String role) throws RoleNotFoundException {
		Role r = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			r = ss.get(Role.class, role);
		}
		return r;
	}

	@Override
	public Role addRole(Role r) throws RoleNotFoundException {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.save(r);
			tx.commit();
		}
		return r;
	}

}
