package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Task;
import com.revature.util.ConnectionUtil;

public class TaskPostgres implements TaskDao {
	
	EmployeeDao ed = new EmployeePostgres();
	
	@Override
	public Task getTaskById(int id) {
		String sql = "select * from tasks where task_id = ?";
		Task task = null;
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id); // 1 refers to first ? to parameterize
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int task_id = rs.getInt("task_id");
				String task_name = rs.getString("task_name");
				String task_description = rs.getString("task_description");
				String completion_status = rs.getString("completion_status");
				int assigned_empl_id = rs.getInt("empl_id");
				Employee assignedEmployee = ed.getEmployeeById(assigned_empl_id);
				Date assignDate = rs.getDate("assign_date");
				LocalDate assign_date = assignDate.toLocalDate();
				Date dueDate = rs.getDate("due_date");
				LocalDate due_date = dueDate.toLocalDate();
				
				task = new Task(task_id, task_name, task_description, completion_status, assignedEmployee, assign_date, due_date);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addTask(Task task) {
		int id = -1;
		String sql = "insert into tasks (task_name, task_description, completion_status, assign_date, due_date, empl_id) values (?,?,?,?,?,?) returning task_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, task.getName());
			ps.setString(2, task.getDescription());
			ps.setString(3, task.getCompletionStatus());
			Date assign_date = Date.valueOf(task.getAssignDate());
			ps.setDate(4, assign_date);
			Date due_date = Date.valueOf(task.getDueDate());
			ps.setDate(5, due_date);
			ps.setInt(6, task.getAssignedEmployee().getId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("task_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean updateTask(Task task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteTask(int id) {
		String sql = "delete from tasks where task_id = ?;";
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsChanged;
	}

	@Override
	public List<Task> getTaskByEmployeeId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
