package com.airlines.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.airlines.dao.CancelDao;

@Controller
public class CancelController {

	@Autowired
	CancelDao cd;
	
	@RequestMapping("/cancel")
	public ModelAndView getCancel(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mv = new ModelAndView( new RedirectView("book"));
		
		int bsno = Integer.parseInt((String)request.getParameter("bsno"));
		
		HttpSession session = request.getSession();
		
		cd.setBsno(bsno);
		
		cd.setUsername((String)(session.getAttribute("username")));
		
		if(cd.cancelled()){
			session.setAttribute("cancelled",true);
		}
		else{
			session.setAttribute("cancellationFailed",true);
		}
		
		return mv;
	}
}
