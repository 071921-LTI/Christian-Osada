package com.one.daos;

import com.one.exceptions.TypeNotFoundException;
import com.one.models.Type;

public interface TypeDao {
	public abstract Type getTypeById(int id) throws TypeNotFoundException;
	public abstract Type getTypeByName(String type) throws TypeNotFoundException;
	public abstract Type addType(Type t) throws TypeNotFoundException;
	public abstract boolean updateType(Type t) throws TypeNotFoundException;
}
