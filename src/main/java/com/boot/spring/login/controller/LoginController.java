package com.boot.spring.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.security.LoginUserDetails;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(@AuthenticationPrincipal LoginUserDetails userDetails) {
		System.out.println("Login Page -> Go");
		
		return "index";
	}
	
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request, @RequestBody String userJSON, @RequestParam Map<String, Object> map) {
		
		String result = "failed";
		
//		if ( loginInfo.isEmpty() ) {
//			result = "failed";
//			System.out.println("Login Info is Null");
//		String a = request.getParameter("user");
//		System.out.println("user : " + a);
//		}
//		
//		String user = (String) loginInfo.get("user");
//		String passwd = (String) loginIc nfo.get("password");
		System.out.println("request Body : " + userJSON.toString());
		System.out.println("request header : " + request.getContentType());
		System.out.println("Map : " + map.size());
		for (String mapkey : map.keySet()){
	        System.out.println("key:"+mapkey+",value:"+map.get(mapkey));
	    }
//		System.out.println("passwd : " + passwd);
		
		result = "ok";
		return result;
	}
}
