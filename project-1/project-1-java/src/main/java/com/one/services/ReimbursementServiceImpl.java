package com.one.services;

import java.util.List;

import com.one.daos.ReimbursementDao;
import com.one.daos.ReimbursementHibernate;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.User;

public class ReimbursementServiceImpl implements ReimbursementService {

	private ReimbursementDao rd = new ReimbursementHibernate();
	
	@Override
	public Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException {
		return rd.getReimbursementById(id);
	}
	@Override
	public List<Reimbursement> getReimbursements() {
		return rd.getReimbursements();
	}
	@Override
	public List<Reimbursement> getReimbursementsbyAuthor(User user) {
		return rd.getReimbursementsbyAuthor(user);
	}
	@Override
	public boolean addReimbursement(Reimbursement reimbursement) {
		return rd.addReimbursement(reimbursement);
	}
	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException {
		return rd.deleteReimbursement(reimbursement);
	}
	@Override
	public boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException {
		return rd.updateReimbursement(reimbursement);
	}
}
