package com.airlines.dao;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Users;

@Component
public class UserDao {
	
	@Autowired
	EntityManager em;
	
	String name;
	String username;
	String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean signup(){
		
		Users user = new Users();
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hash);
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return true;
		
	}
	public boolean existUser(){
		return (em.find(Users.class, username) != null ? true : false);
		
	}
}