package com.one.services;

import java.util.List;

import com.one.daos.ReimbursementCollection;
import com.one.daos.ReimbursementDao;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	private ReimbursementDao rd = new ReimbursementCollection();
	
	@Override
	public Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException {
		return rd.getReimbursementById(id);
	}
	@Override
	public List<Reimbursement> getReimbursements() {
		return rd.getReimbursements();
	}
	@Override
	public List<Reimbursement> getReimbursementsbyAuthor(int id) {
		return rd.getReimbursementsbyAuthor(id);
	}
	@Override
	public boolean addReimbursement(Reimbursement reimbursement) {
		return rd.addReimbursement(reimbursement);
	}
	@Override
	public boolean deleteReimbursement(int id) throws ReimbursementNotFoundException {
		return rd.deleteReimbursement(id);
	}
	@Override
	public boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException {
		return rd.updateReimbursement(reimbursement);
	}
}
