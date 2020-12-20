package com.airlines.controllers;

import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.airlines.dao.AddFlightDao;
import com.airlines.dao.AddTimeTableDao;
import com.airlines.dao.AddCityDao;
import com.airlines.dao.BookingDao;
import com.airlines.pojos.Bookings;
import com.airlines.dao.CitiesDao;
import com.airlines.dao.FlightsDao;

@Controller
public class AddController {

	@Autowired
	CitiesDao cd;
	@Autowired
	FlightsDao fd;
	@Autowired
	BookingDao bd;
	@Autowired
	AddFlightDao afd;
	@Autowired
	AddCityDao acd;
	@Autowired
	AddTimeTableDao attd;
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView getAdd() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		Set<String> set = cd.cities();
		Set<String> setF = fd.flights();
		Set<Bookings> setB = bd.historyOfAll();
		
		mv.addObject("Set", set);
		mv.addObject("BookingsLength", setB.size());
		mv.addObject("SetFlight", setF);
		mv.addObject("SetBooking", setB);
		
		mv.setViewName("add");
		return mv;
	}
	
	@RequestMapping(value="/addCity",method=RequestMethod.POST)
	public ModelAndView postAddCity(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(new RedirectView("add"));
		
		HttpSession session = request.getSession();
		
		String code = request.getParameter("code");
		String name = request.getParameter("city");
		
		acd.setCity(name);
		acd.setCode(code);
		
		if(acd.added()){
			session.setAttribute("cityAdded", true);
		}
		else{
			session.setAttribute("cityAdded", false);
		}
		
		return mv;		
	}
	
	@RequestMapping(value="/addFlights",method=RequestMethod.POST)
	public ModelAndView postAddFlight(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView(new RedirectView("add"));
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
	
		System.out.println(code+" "+name);
		
		afd.setCode(code);
		afd.setFlight(name);
		
		if(afd.added()){
			request.getSession().setAttribute("flightAdded", true);
			System.out.println("FLIGHT ADDED SUCCESSFULLY");
		}
		else{
			System.out.println("FLIGHT ADDED FAILED");
			request.getSession().setAttribute("flightAdded", false);
		}
		
		return mv;
	}
	
	@RequestMapping(value="/addTimeTable",method=RequestMethod.POST)
	public ModelAndView postAddTimeTable(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView(new RedirectView("add"));
		
		HttpSession session = request.getSession();
		
		String fid = request.getParameter("flights");
		String departure_city = request.getParameter("fromCity");
		String arrival_city = request.getParameter("toCity");
		String departure_time = request.getParameter("departureTime");
		String arrival_time = request.getParameter("arrivalTime");

		int price = Integer.parseInt(request.getParameter("price"));
	
		fid = fid.substring(fid.indexOf("(")+1,fid.length()-1);
		departure_city = departure_city.substring(departure_city.indexOf("(")+1,departure_city.length()-1);
		arrival_city = arrival_city.substring(arrival_city.indexOf("(")+1,arrival_city.length()-1);
		attd.setArrival_city(arrival_city);
		attd.setArrival_time(arrival_time);
		attd.setDeparture_city(departure_city);
		attd.setDeparture_time(departure_time);
		attd.setFid(fid);
		attd.setPrice(price);
		
		if(attd.added()){
			session.setAttribute("timeTableAdded", true);
		}
		else{
			session.setAttribute("timeTableAdded", false);
		}

		
		
		return mv;
		
	}
}
