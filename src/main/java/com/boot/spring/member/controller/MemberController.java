package com.boot.spring.member.controller;

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
import com.boot.spring.member.service.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private CommonUtils common;
		
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String info() {
		System.out.println("UserInfo Page -> Go");
		
		return "index";
	}
	
	@RequestMapping(value="/userUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String userUpdate(HttpServletRequest request, @RequestBody String userJSON, 
			@RequestParam Map<String, Object> map) throws Exception{
		System.out.println("[userUpdate] Start");
		String result = "failed";
		Gson gson = new Gson();
		Map<String, Object> response;
		
		Map<String, String> parseData = common.parseJsonToMap(userJSON);
		int success = memberService.userUpdate(parseData);
		
		if ( success == -1 ) {
			return result;
		} else {
			result = "success";
			return result;
		}		
	}
}
