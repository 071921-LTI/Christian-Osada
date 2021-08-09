package com.one.services;

import java.util.List;

import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;

public interface ReimbursementService {

	//Checks if inputed user exists
	public abstract Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException;
	public abstract List<Reimbursement> getReimbursements();
	public abstract List<Reimbursement> getReimbursementsbyAuthor(int id);
	public abstract boolean addReimbursement(Reimbursement reimbursement);
	public abstract boolean deleteReimbursement(int id) throws ReimbursementNotFoundException;
	public abstract boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException;
}
