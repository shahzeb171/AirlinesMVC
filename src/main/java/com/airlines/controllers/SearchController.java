package com.airlines.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.airlines.dao.TimeTableDao;
import com.airlines.pojos.Time_Table;

@Controller
public class SearchController {

	@Autowired
	TimeTableDao ttd;
	@RequestMapping("/search")
	public ModelAndView postSearch(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		String toCity       = request.getParameter("toCity").toString(),fromCity=request.getParameter("fromCity").toString();
		String toCityCode   = toCity.substring(toCity.indexOf('(')+1,toCity.indexOf(')'));
		String fromCityCode = fromCity.substring(fromCity.indexOf('(')+1,fromCity.indexOf(')'));
		
		ttd.setFromCity(fromCityCode);
		ttd.setToCity(toCityCode);
		List<Time_Table> list = ttd.TimeTableFetcher();
			
		mv.addObject("List", list);
		mv.setViewName("search");
		
		return mv;
	}
}
