package com.airlines.dao;

import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Bookings;

@Component
public class CancelDao {
	@Autowired
	EntityManager em;
	
	int bsno;
	String username;
	
	
	public int getBsno() {
		return bsno;
	}


	public void setBsno(int bsno) {
		this.bsno = bsno;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean cancelled(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Bookings> cd = cb.createCriteriaDelete(Bookings.class);
		Root<Bookings> rt = cd.from(Bookings.class);
		
		Predicate p1 =  cb.equal( rt.get("bsno"), bsno);
		Predicate p2 = cb.equal( rt.get("username"), username);
		
		Predicate p3 = cb.and(p1,p2);
		
		cd.where( p3);
		em.getTransaction().begin();
		int result = em.createQuery(cd).executeUpdate();
		em.getTransaction().commit();
		System.out.println("DELETE RESULT "+result);
		return true;
	}
}