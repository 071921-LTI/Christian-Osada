package com.one.daos;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.User;
import com.one.util.HibernateUtil;

public class ReimbursementHibernate implements ReimbursementDao{

	@Override
	public Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException {
		Reimbursement r = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			r = ss.get(Reimbursement.class, id);
		}
		return r;
	}

	@Override
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> r = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			r = ss.createQuery("FROM Reimbursement", Reimbursement.class).list();
		}
		return r;
	}

	@Override
	public List<Reimbursement> getReimbursementsbyAuthor(User user) {
		List<Reimbursement> r = null;
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			r = ss.createQuery("FROM Reimbursement R WHERE R.author = " + user, Reimbursement.class).list();
		}
		return r;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		r.setSubmitted(timestamp);
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.save(r);
			tx.commit();
		}
		return true;
	}

	@Override
	public boolean deleteReimbursement(Reimbursement r) throws ReimbursementNotFoundException {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.delete(r);
			tx.commit();
		}
		return true;
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) throws ReimbursementNotFoundException {
		try(Session ss = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = ss.beginTransaction();
			ss.update(r);
			tx.commit();
		}
		return true;
	}
}
