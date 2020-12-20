package com.airlines.dao;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Flights;

@Component
public class AddFlightDao {
	@Autowired
	EntityManager em;
	
	String code;
	String flight;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public boolean added(){
		Flights f = new Flights();
		f.setFid(code);
		f.setFname(flight);
		
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		return true;

	}
}
