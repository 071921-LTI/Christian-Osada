package com.one.daos;

import com.one.exceptions.RoleNotFoundException;
import com.one.models.Role;

public interface RoleDao {
	public abstract Role getRoleById(int id) throws RoleNotFoundException;
	public abstract Role getRoleByName(String role) throws RoleNotFoundException;
	public abstract Role addRole(Role r) throws RoleNotFoundException;
	public abstract boolean updateRole(Role r) throws RoleNotFoundException;
}
