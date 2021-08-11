package com.one.daos;

import java.util.List;
import com.one.exceptions.ReimbursementNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.User;

public interface ReimbursementDao {

	public abstract Reimbursement getReimbursementById(int id) throws ReimbursementNotFoundException;
	public abstract List<Reimbursement> getReimbursements();
	public abstract List<Reimbursement> getReimbursementsbyAuthor(User user);
	public abstract boolean addReimbursement(Reimbursement reimbursement);
	public abstract boolean deleteReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException;
	public abstract boolean updateReimbursement(Reimbursement reimbursement) throws ReimbursementNotFoundException;
}
