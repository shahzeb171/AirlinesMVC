package com.airlines.dao;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Flights;

import java.util.List;
import java.sql.SQLException;
import java.util.HashSet;

import java.util.Set;


@Component
public class FlightsDao {

	@Autowired
	EntityManager em;
	
public Set<String> flights() throws SQLException, ClassNotFoundException{
		
	
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
         CriteriaQuery<Flights> criteriaQuery = criteriaBuilder.createQuery(Flights.class);
        
         Root<Flights> query = criteriaQuery.from(Flights.class);
         criteriaQuery.select(query);
         TypedQuery<Flights> typedQuery = em.createQuery(criteriaQuery);
         List<Flights> allData = typedQuery.getResultList();
         Set<String> set = new HashSet<String>();
         for(Flights f : allData) {
        	 String s = f.getFname()+"("+f.getFid()+")";
        	 set.add(s);
         }
       
         return set;
			}
}
