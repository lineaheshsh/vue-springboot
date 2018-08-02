package com.boot.spring.member.controller;

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
public class MemberController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	private CommonUtils common;
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String info() {
		System.out.println("UserInfo Page -> Go");
		
		return "index";
	}
}
