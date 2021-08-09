package com.one.daos;

import java.util.List;

import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;

public class ReimbursementCollection implements ReimbursementDao {

	@Override
	public Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsbyAuthor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteReimbursement(int id) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
}
