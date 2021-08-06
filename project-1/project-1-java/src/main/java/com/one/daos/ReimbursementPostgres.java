package com.one.daos;

import java.util.List;

import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;
public class ReimbursementPostgres implements ReimbursementDao{

	public Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getReimbursementsbyAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteReimbursement(int id) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
