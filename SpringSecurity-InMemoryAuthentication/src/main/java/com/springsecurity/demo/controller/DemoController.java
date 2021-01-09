package com.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHomePage() {
		return "home-page";
	}
	
	
	@GetMapping("/managers")
	public String showManagersPage() {
		return "managers";
	}
	
	@GetMapping("/system")
	public String showSystemPage() {
		return "system";
	}
	
	
	@GetMapping("/leaders")
	public String showLeadersPage() {
		return "leaders";
	}

}
