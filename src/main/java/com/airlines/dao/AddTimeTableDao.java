package com.airlines.dao;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Time_Table;

@Component
public class AddTimeTableDao {
	
	@Autowired
	EntityManager em;
	
	String fid;
	String departure_city;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getDeparture_city() {
		return departure_city;
	}
	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}
	public String getArrival_city() {
		return arrival_city;
	}
	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	String arrival_city ;
	String departure_time;
	String arrival_time ;
	int price ;
	
	public boolean added(){
		Time_Table tt = new Time_Table();
		tt.setFid(fid);
		tt.setDeparture_ccode(departure_city);
		tt.setArrival_ccode(arrival_city);
		tt.setPrice(price);
		tt.setArrival_time(arrival_time);
		tt.setDeparture_time(departure_time);
		
		em.getTransaction().begin();
		em.persist(tt);
		em.getTransaction().commit();
		
		return true;
	}
}