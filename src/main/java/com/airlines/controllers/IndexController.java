package com.airlines.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.airlines.dao.CitiesDao;

@Controller
public class IndexController {
	
	@Autowired
	CitiesDao cd;
	
	@RequestMapping("/")
	public ModelAndView getHTML() {
		return new ModelAndView(new RedirectView("index"));
	}
	
	@RequestMapping("/index")
	public ModelAndView getIndex() {
		ModelAndView mv = new ModelAndView();
		Set<String> set = cd.cities();
		
		mv.addObject("Set", set);
		mv.setViewName("index");
		
		return mv;
	}
}
