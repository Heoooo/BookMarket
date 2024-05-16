package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginfailed")
	public String loginerror(Model model) {
		model.addAttribute("error","true");
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		return "login";
	}
}
