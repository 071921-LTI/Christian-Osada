package com.revature;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.daos.TaskDao;
import com.revature.daos.TaskPostgres;
import com.revature.models.Employee;
import com.revature.models.Task;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		
		EmployeeDao ed = new EmployeePostgres();
		TaskDao td = new TaskPostgres();
//		List<Employee> emps = ed.getEmployees();
//		for(Employee e : emps) {
//			System.out.println(e);
//		}
		
		/*
		 * Employee e = new Employee(); e.setId(8); e.setName("KevinTest");
		 * e.setEmail("SomeEmail1"); e.setPassword("badPass11111"); e.setRole("Clown");
		 * e.setSalary(20.0); e.setManager(new Employee(1));
		 * 
		 * try { ConnectionUtil.getConnectionFromEnv();
		 * System.out.println(ed.updateEmployee(e)); System.out.println("Worked?"); }
		 * catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */
		
		//System.out.println(td.getTaskById(1));
		
		Task task = new Task();
		task.setName("Task1");
		task.setDescription("A task");
		task.setCompletionStatus("unfinished");
		task.setAssignedEmployee(ed.getEmployeeById(1));
		task.setAssignDate(LocalDate.now());
		task.setDueDate(LocalDate.now());
		
		td.addTask(task);
		
		/*Employee e = new Employee();
		e.setName("KevinTest");
		e.setEmail("SomeEmail1");
		e.setPassword("badPass1");
		e.setRole("Clown");
		e.setSalary(20.0);
		e.setManager(new Employee(1));
		
		int genId = ed.addEmployee(e);
		System.out.println("The generated id is:" + genId);
		System.out.println(ed.getEmployeeById(genId));
		System.out.println("Number of rows changed:" + ed.deleteEmployee(genId));
		
		try {
			ConnectionUtil.getConnectionFromEnv();
			System.out.println("Works fine.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
