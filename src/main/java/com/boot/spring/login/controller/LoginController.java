package com.boot.spring.login.controller;

import java.util.HashMap;
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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String logout() {
//		public String login(@AuthenticationPrincipal LoginUserDetails userDetails) {
		System.out.println("UserList Page -> Go");
		
		return "index";
	}
	
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request, @RequestBody String userJSON, 
			@RequestParam Map<String, Object> map) throws Exception{
		System.out.println("[loginCheck] Start");
		String result = "failed";
		Gson gson = new Gson();
		Map<String, Object> response;
		
		MemberVO memberVO = loginService.loginCheck(userJSON);
		
		if ( memberVO == null ) {
			System.out.println("[loginCheck] memberVO is null");
			result = "failed";
			return result;
		}
		else {
			response = new HashMap<String, Object>();
			String jwt = Jwts.builder()
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("issueDate", System.currentTimeMillis())
					.setSubject(memberVO.toString())
					.signWith(SignatureAlgorithm.HS256, "luvookSecretaaaaaaaabbbbbbbbbbbbbbbbb".getBytes("UTF-8"))
					.compact();
				    
			System.out.println("jwt >>>>>>>>>>> " + jwt);
			System.out.println("[loginCheck] End");
			
			response.put("token", jwt);
			response.put("member", memberVO);
			
			return gson.toJson(response);
		}
		
	}
	
	@RequestMapping(value="/getTotalTodayLoginCount", method=RequestMethod.GET)
	@ResponseBody
	public String getTodayLoginCount(HttpServletRequest request ) {
		System.out.println("[getTotalTodayLoginCount] Start");	

		Gson gson = new Gson();
		LoginCountVO loginCountVO = loginService.getTotalTodayLoginCount();
		
		return gson.toJson(loginCountVO);
		
	}
	
	@RequestMapping(value="/getTodayAccessRanking10", method=RequestMethod.GET)
	@ResponseBody
	public String getTodayAccessRanking10(HttpServletRequest request ) {
		System.out.println("[getTodayAccessRanking10] Start");	

		Gson gson = new Gson();
		List<LoginCountVO> loginCountVO = loginService.getTodayAccessRanking10();
		
		return gson.toJson(loginCountVO);
		
	}
	
	@RequestMapping(value="/accessCountAdd", method=RequestMethod.POST)
	@ResponseBody
	public String accessCountAdd(HttpServletRequest request, @RequestBody String jsonData) {
		
		Gson gson = new Gson();
		List<LoginCountVO> loginCountVO = null;
		System.out.println("[accessCountAdd] jsonData : " + jsonData);
		Map<String, String> parseData = common.parseJsonToMap(jsonData);
		int seq = Integer.parseInt(parseData.get("seq"));
		int success = loginService.loginAccessCount(seq);
		
		if ( success == 1 ) {
			loginCountVO = loginService.getWeekLoginCount(seq);
//			System.out.println("loginCountVo : " + loginCountVO.toString());
			return gson.toJson(loginCountVO);
		} else 	return null;
		
	}
}
