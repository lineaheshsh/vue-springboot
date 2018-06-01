package com.boot.spring.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.login.service.LoginService;
import com.boot.spring.member.vo.MemberVO;
import com.google.gson.Gson;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
//		public String login(@AuthenticationPrincipal LoginUserDetails userDetails) {
		System.out.println("Login Page -> Go");
		
		return "index";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
//		public String login(@AuthenticationPrincipal LoginUserDetails userDetails) {
		System.out.println("Main Page -> Go");
		
		return "index";
	}
	
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request, @RequestBody String userJSON, @RequestParam Map<String, Object> map) throws Exception{
		
		String result = "failed";
		Gson gson = new Gson();
		
		MemberVO memberVO = loginService.loginCheck(userJSON);
		
		if ( memberVO == null ) {
			result = "failed";
			return result;
		}
		else 
			return gson.toJson(memberVO);
		
		
	}
}
