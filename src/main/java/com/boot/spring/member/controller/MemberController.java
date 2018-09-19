package com.boot.spring.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.common.util.CommonUtils;
import com.boot.spring.login.service.LoginService;
import com.boot.spring.member.service.MemberService;
import com.boot.spring.member.vo.MemberVO;
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
	
	@RequestMapping(value="/user/add", method=RequestMethod.GET)
	public String userAdd() {
		System.out.println("UserAdd Page -> Go");
		
		return "index";
	}
	
	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String userList() {
		System.out.println("UserList Page -> Go");
		
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
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	@ResponseBody
	public String userAdd(@RequestBody MemberVO member) throws Exception{
		System.out.println("[userAdd] Start");
		String result = "failed";
		Gson gson = new Gson();
		Map<String, Object> response;
		
		int success = memberService.userInsert(member);
		
		if ( success == -1 ) {
			return result;
		} else {
			result = "success";
			return result;
		}
	}
	
	@RequestMapping(value="/getUserList", method=RequestMethod.GET)
	@ResponseBody
	public String getUserList() throws Exception{
		System.out.println("[getUserList] Start");
		Gson gson = new Gson();
	
		List<MemberVO> result = memberService.getUserList();
		
		return gson.toJson(result);
	}
}
