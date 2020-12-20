package com.airlines.dao;

import java.sql.*;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Admin;
import com.airlines.pojos.Users;


@Component
public class UserAdminCheckerDao {
	
	@Autowired
	EntityManager em;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
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
	
	String table;
	String username;
	String password;
	String name;
	
	public boolean check() throws ClassNotFoundException, SQLException{
		
		String hashDB;
		if(table.equals("users")) {
			Users user = em.find(Users.class, username);
			if(user == null) return false;
			hashDB = user.getPassword();
			name = user.getName();
		}else {
			Admin admin = em.find(Admin.class, username);
			if(admin == null) return false;
			hashDB = admin.getPassword();	
			name = admin.getName();
		}
			if(BCrypt.checkpw(password, hashDB))
				return true;
			return false;
	}
	public String getName(){
		return name;
	}
}