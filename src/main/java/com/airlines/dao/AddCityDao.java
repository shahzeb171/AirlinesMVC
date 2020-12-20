package com.airlines.dao;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Cities;

@Component
public class AddCityDao {
	
	@Autowired
	EntityManager em;
	
	String code;
	String city;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public boolean added(){
		
		Cities c = new Cities();
		c.setCcode(code);
		c.setCname(city);
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		return true;
		
	}
}
