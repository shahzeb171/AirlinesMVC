package com.airlines.dao;

import javax.persistence.criteria.Predicate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Time_Table;


@Component
public class TimeTableDao {
	
	@Autowired
	EntityManager em;
	
	String toCity;
	String fromCity;
	
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	
	public List<Time_Table> TimeTableFetcher() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Time_Table> cq = cb.createQuery(Time_Table.class);
		
		Root<Time_Table> rt = cq.from(Time_Table.class);
		
		Predicate p1 = cb.equal(rt.get("arrival_ccode"), toCity);
		Predicate p2 =  cb.equal(rt.get("departure_ccode"), fromCity);
		Predicate p3 =  cb.and(p1,p2);
		
		cq.where(p3);
		
		TypedQuery<Time_Table> tq = em.createQuery(cq);
		List<Time_Table> list = tq.getResultList();
		
		
		return list;
	}
}
