package com.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/home.do")
	public String homePage() {
		return "home";
	}

	@RequestMapping("/register.do")
	public String registerPage() {
		return "register";
	}

	@RequestMapping("/login.do")
	public ModelAndView loginPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("target", request.getParameter("target"));
		return mav;
	}
}
