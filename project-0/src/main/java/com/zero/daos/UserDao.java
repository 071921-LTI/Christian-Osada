package com.zero.daos;
import java.util.ArrayList;
import java.util.List;
//import com.zero.exceptions.UserNameTakenException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.User;

public interface UserDao {
	public abstract User getUserById(int id) throws UserNotFoundException;
	public abstract List<User> getUsers();
	public abstract boolean addUser(User user);
	public abstract int deleteUser(int id) throws UserNotFoundException;
	public abstract User getUserByName(String name) throws UserNotFoundException;
}