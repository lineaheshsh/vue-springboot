package com.boot.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String root() {
		
		System.out.println("Start Vue.js + SpringBoot Project!");
		
		return "index";
	}
}
