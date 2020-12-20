package com.airlines.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.airlines.dao.UserAdminCheckerDao;

@Controller
public class LoginController {

	@Autowired
	UserAdminCheckerDao uacd;
	
	@RequestMapping(value="/loginAdmin",method=RequestMethod.GET)
	public ModelAndView getloginAdmin() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginAdmin");
		
		return mv;
	}
	
	@RequestMapping(value="/loginUser", method=RequestMethod.GET)
	public ModelAndView getloginUser() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("loginUser");
		
		return mv;
	}
	
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public ModelAndView postloginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView(new RedirectView("index"));
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		uacd.setPassword(password);
		uacd.setTable("users");
		uacd.setUsername(username);
		
		HttpSession session = request.getSession();
			if(uacd.check()){
				
				session.setAttribute("loggedinUser",true);
				session.setAttribute("username",username);
				session.setAttribute("loggedin",true);
				session.setAttribute("name",uacd.getName());
				
			}
			else{
				mv.addObject("failedLogin", true);
				mv.setViewName("loginUser");
			}
		
		
		return mv;
	}
	@RequestMapping(value="/loginAdmin", method=RequestMethod.POST)
	public ModelAndView postLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		ModelAndView mv = new ModelAndView(new RedirectView("index"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		uacd.setPassword(password);
		uacd.setUsername(username);
		uacd.setTable("admin");
		
		HttpSession session = request.getSession();
		if(uacd.check()){
				session.setAttribute("loggedinAsAdmin", true);
				session.removeAttribute("failedLoginAdmin");
				session.setAttribute("usernameAdmin",username);
				session.setAttribute("loggedinAdmin",true);
				session.setAttribute("nameAdmin",uacd.getName());
				
			}
			else{
				session.setAttribute("failedLoginAdmin", true);
				mv.setViewName("loginAdmin");
			}
		
		return mv;
	}
}
