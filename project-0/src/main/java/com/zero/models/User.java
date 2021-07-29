package com.zero.models;

import java.io.Serializable;
import com.zero.models.User;

public class User implements Serializable{

	private int userId;
	private String username;
	private String password;
	private String permissionLevel;
	
	public User(int userId, String username, String password, String permissionLevel) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.permissionLevel = permissionLevel;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPermissionLevel() {
		return permissionLevel;
	}
	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((permissionLevel == null) ? 0 : permissionLevel.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permissionLevel == null) {
			if (other.permissionLevel != null)
				return false;
		} else if (!permissionLevel.equals(other.permissionLevel))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", permissionLevel="
				+ permissionLevel + "]";
	}
}
