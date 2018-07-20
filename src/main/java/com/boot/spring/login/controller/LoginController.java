package com.boot.spring.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.common.util.CommonUtils;
import com.boot.spring.login.service.LoginService;
import com.boot.spring.login.vo.LoginCountVO;
import com.boot.spring.member.vo.MemberVO;
import com.google.gson.Gson;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	private CommonUtils common;
	
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
	public String loginCheck(HttpServletRequest request, @RequestBody String userJSON, 
			@RequestParam Map<String, Object> map) throws Exception{
		System.out.println("[loginCheck] Start");
		String result = "failed";
		Gson gson = new Gson();
		
		MemberVO memberVO = loginService.loginCheck(userJSON);
		
		if ( memberVO == null ) {
			System.out.println("[loginCheck] memberVO is null");
			result = "failed";
			return result;
		}
		else {
			
			System.out.println("[loginCheck] End");
			return gson.toJson(memberVO);
		}
		
	}
	
	@RequestMapping(value="/getTodayLoginCount", method=RequestMethod.GET)
	@ResponseBody
	public String getTodayLoginCount(HttpServletRequest request ) {
		System.out.println("[getTodayLoginCount] Start");	

		Gson gson = new Gson();
		int seq = Integer.parseInt(request.getParameter("seq"));
		LoginCountVO loginCountVO = loginService.getTodayLoginCount(seq);
			
		return gson.toJson(loginCountVO);
		
	}
	
	@RequestMapping(value="/accessCountAdd", method=RequestMethod.POST)
	@ResponseBody
	public String accessCountAdd(HttpServletRequest request, @RequestBody String jsonData) {
		
		Gson gson = new Gson();
		List<LoginCountVO> loginCountVO = null;
		Map<String, String> parseData = common.parseJsonToMap("seq", jsonData);
		int seq = Integer.parseInt(parseData.get("seq"));
		int success = loginService.loginAccessCount(seq);
		
		if ( success == 1 ) {
			loginCountVO = loginService.getWeekLoginCount(seq);
			return gson.toJson(loginCountVO);
		} else 	return null;
		
	}
}
