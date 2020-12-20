package com.airlines.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.airlines.dao.BookingDao;

@Controller
public class BookingController {

	@Autowired
	BookingDao bd;
	
	@RequestMapping("/book")
	public ModelAndView getBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") == null)
			return new ModelAndView(new RedirectView("loginUser"));
		
		
		String username = (String)session.getAttribute("username");
		if(request.getParameter("sno")!=null){
		int sno = Integer.parseInt((String)request.getParameter("sno"));

		bd.setSno(sno);
		bd.setUsername(username);
		
		if(bd.Booked()){
			session.setAttribute("Bookings", bd.history());
			session.setAttribute("BookingsLength", bd.history().size());
			session.setAttribute("booked", true);
			mv.setViewName("book");
		}
		else
			return new ModelAndView(new RedirectView("index"));
		}
		else{
				
			bd.setUsername(username);
			session.setAttribute("Bookings", bd.history());
			session.setAttribute("BookingsLength", bd.history().size());
			mv.setViewName("book");		
		}
		return mv;
	}
}
