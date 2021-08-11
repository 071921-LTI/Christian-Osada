package com.one.daos;

import com.one.exceptions.StatusNotFoundException;
import com.one.models.Status;

public interface StatusDao {
	public abstract Status getStatusById(int id) throws StatusNotFoundException;
	public abstract Status getStatusByName(String status) throws StatusNotFoundException;
	public abstract Status addStatus(Status s) throws StatusNotFoundException;
}
