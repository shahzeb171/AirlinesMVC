package com.airlines.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Bookings;
import com.airlines.pojos.Time_Table;

@Component
public class BookingDao {
	@Autowired
	EntityManager em;
	
	int sno;
	String username;
	String fid;
	String departure_ccode ;
	String arrival_ccode;
	String departure_time;
	String arrival_time;
	int price;
	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getDetails(){
		
		Time_Table tt = em.find(Time_Table.class, sno);
		
		if(tt == null) return false;
		
		fid=tt.getFid();
		departure_ccode = tt.getDeparture_ccode();
		arrival_ccode = tt.getArrival_ccode();
		departure_time = tt.getDeparture_time();
		arrival_time = tt.getArrival_time();
		price = tt.getPrice();
			return true;
	}
	
	public boolean Booked(){
		
		boolean get = getDetails();
		if(!get)return false;
		
		Bookings bk = new Bookings();
		bk.setArrival_ccode(arrival_ccode);
		bk.setArrival_time(arrival_time);
		bk.setDeparture_ccode(departure_ccode);
		bk.setDeparture_time(departure_time);
		bk.setPrice(price);
		bk.setUsername(username);
		
		em.getTransaction().begin();
		em.persist(bk);
		em.getTransaction().commit();
		
		return true;
	}
	
	public Set<Bookings> history(){
		Set<Bookings> set = new HashSet<Bookings>();
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bookings> cq = cb.createQuery(Bookings.class);
		
		Root<Bookings> rt = cq.from(Bookings.class);
		ParameterExpression<String> pe = cb.parameter(String.class);
		cq.select(rt).where(cb.equal(rt.get("username"), pe));
		
		TypedQuery<Bookings> query = em.createQuery(cq);
		 query.setParameter(pe, username);
		 List<Bookings> results = query.getResultList();
		 
		    
	        for(Bookings bk : results) {
	        	set.add(bk);
	        }
	       
	        return set;

	}
	
	public Set<Bookings> historyOfAll(){
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Bookings> criteriaQuery = criteriaBuilder.createQuery(Bookings.class);
       
        Root<Bookings> query = criteriaQuery.from(Bookings.class);
        criteriaQuery.select(query);
        TypedQuery<Bookings> typedQuery = em.createQuery(criteriaQuery);
        
        List<Bookings> allData = typedQuery.getResultList();
        
        Set<Bookings> set = new HashSet<Bookings>();
        
        for(Bookings bk : allData) {
        	set.add(bk);
        }
       
        return set;
				
	}
}
