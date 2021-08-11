package com.one;

import com.one.daos.ReimbursementDao;
import com.one.daos.ReimbursementHibernate;
import com.one.daos.RoleDao;
import com.one.daos.RoleHibernate;
import com.one.daos.StatusDao;
import com.one.daos.StatusHibernate;
import com.one.daos.TypeDao;
import com.one.daos.TypeHibernate;
import com.one.daos.UserDao;
import com.one.daos.UserHibernate;
import com.one.exceptions.RoleNotFoundException;
import com.one.exceptions.StatusNotFoundException;
import com.one.exceptions.TypeNotFoundException;
import com.one.exceptions.UserNotFoundException;
import com.one.models.Reimbursement;
import com.one.models.Role;
import com.one.models.Status;
import com.one.models.Type;
import com.one.models.User;

public class Driver {
	
	static StatusDao sd = new StatusHibernate();
	static RoleDao rd = new RoleHibernate();
	static TypeDao td = new TypeHibernate();
	static UserDao ud = new UserHibernate();
	static ReimbursementDao rmd = new ReimbursementHibernate();
	
	public static void main(String[] args) {
		try {
			sd.addStatus(new Status("Pending"));
			sd.addStatus(new Status("Accepted"));
			sd.addStatus(new Status("Rejected"));
			
			rd.addRole(new Role("Employee"));
			rd.addRole(new Role("Manager"));
			
			td.addType(new Type ("Business"));
			td.addType(new Type ("Deductible"));
			td.addType(new Type ("Medical"));
			td.addType(new Type ("Travel"));
			
			ud.addUser(new User("username", "password", "first", "last", "email", rd.getRoleById(1)));
			rmd.addReimbursement(new Reimbursement(200.00, "Driver ran over my foot", ud.getUserById(1), null, sd.getStatusById(1), td.getTypeById(1)));
			System.out.println(sd.getStatusById(1));
			System.out.println(ud.getUserById(1).getRole());
		} catch (StatusNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RoleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TypeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
