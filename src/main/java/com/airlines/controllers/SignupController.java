package com.airlines.controllers;

import com.airlines.dao.UserDao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class SignupController {

	@Autowired
	UserDao ud;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView getSignup() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		return mv;
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView postSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
	ModelAndView mv = new ModelAndView(new RedirectView("index"));
	
	String name = request.getParameter("name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	HttpSession session = request.getSession();

	ud.setName(name);
	ud.setUsername(username);
	ud.setPassword(password);
	
	if(!ud.existUser() && ud.signup()){
		
		session.setAttribute("loggedinUser",true);
		session.setAttribute("username",username);
		session.setAttribute("loggedin",true);
		session.setAttribute("name",name);
		
		
		
		System.out.println("CREATED ACCOUNT SUCCESSFULLY");
	}
	else{
		session.setAttribute("failedSignup", true);

		mv.setViewName("signup");
	}
	return mv;
	}
	
}
