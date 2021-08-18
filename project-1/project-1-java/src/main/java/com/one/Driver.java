package com.one;

import java.sql.Timestamp;
import java.util.Date;

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
import com.one.exceptions.ReimbursementNotFoundException;
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
			
//			User u1 = ud.getUserById(2);
//			User u2 = ud.getUserById(4);
//			Reimbursement r1 = rmd.getReimbursementById(5);
			
//			sd.addStatus(new Status("Pending"));
//			sd.addStatus(new Status("Accepted"));
//			sd.addStatus(new Status("Rejected"));
//			
//			rd.addRole(new Role("Employee"));
//			rd.addRole(new Role("Manager"));
//			
//			td.addType(new Type ("Business"));
//			td.addType(new Type ("Deductible"));
//			td.addType(new Type ("Medical"));
//			td.addType(new Type ("Travel"));
//			
//			ud.addUser(new User("username", "password", "first", "last", "email", rd.getRoleById(1)));
//			ud.addUser(new User("username2", "password2", "first2", "last2", "email2", rd.getRoleById(1)));
//			ud.addUser(new User("manager", "manager", "managerFirst", "managerLast", "managerEmail", rd.getRoleById(2)));
//			
//			rmd.addReimbursement(u1);
//			rmd.addReimbursement(u2);
		
//			ud.deleteUser(ud.getUserById(1));
//			rmd.deleteReimbursement(rmd.getReimbursementById(2));
			
//			System.out.println(rmd.getReimbursementsbyAuthor(ud.getUserById(2)));
//			System.out.println(sd.getStatusById(1));
//			System.out.println(ud.getUserById(1).getRole());
//		} catch (UserNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
			
//			Date date = new Date();
//			Timestamp timestamp = new Timestamp(date.getTime());
//			
//			//Not needed in traditional test
//			r1.setSubmitted(timestamp);
//			
//			r1.setResolver(u1);
//			r1.setResolved(timestamp);
//			
//			rmd.updateReimbursement(r1);
//			System.out.println(rmd.getReimbursementsbyAuthor(u1));
			
			//Comeback to this
//			ud.deleteUser(ud.getUserById(2));
			
//			System.out.println(rmd.getReimbursementsbyAuthor(ud.getUserById(2)));
		
	}

}
