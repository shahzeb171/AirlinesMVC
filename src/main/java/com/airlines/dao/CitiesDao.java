package com.airlines.dao;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airlines.pojos.Cities;

@Component
public class CitiesDao {
	@Autowired
	EntityManager em;
	
	public Set<String> cities() {
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cities> cq = cb.createQuery(Cities.class);
		
		Root<Cities> rt = cq.from(Cities.class);
		cq.select(rt);
		
		TypedQuery<Cities> tq = em.createQuery(cq);
		List<Cities> list = tq.getResultList();
		Set<String> set = new HashSet<String>();
		for(Cities c : list){
			String s = c.getCname()+"("+c.getCcode()+")";
			set.add(s);
		}
		System.out.println("from set "+set);
		return set;
	}
}
