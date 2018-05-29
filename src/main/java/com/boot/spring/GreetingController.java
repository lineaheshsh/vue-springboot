package com.boot.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.login.service.LoginService;

@Controller
public class GreetingController {
	
	@Autowired
	LoginService loginService;

	@RequestMapping("/greeting")
	public String greeting() {
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		
//		MemberRole role = new MemberRole();
//		MemberVO member = new MemberVO();
//		member.setM_id("changho.lee");
//		member.setM_email("lineaheshsh@naver.com");
//		member.setM_pw(passwordEncoder.encode("1111"));
//		System.out.println("password : " + member.getM_pw());
//		member.setM_name("이창호");
//		member.setM_gender('m');
//		role.setRoleName("BASIC");
//		member.setRoles(Arrays.asList(role));
//		
//		loginService.join(member);
		
		
		System.out.println("greeting in!");
		return "index";
	}

}