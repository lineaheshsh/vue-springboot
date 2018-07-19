package com.boot.spring.login.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.login.dao.LoginDAO;
import com.boot.spring.login.vo.LoginCountVO;
import com.boot.spring.login.vo.LoginVO;
import com.boot.spring.member.vo.MemberVO;

@Service("loginService")
public class LoginService {

	@Autowired
	LoginDAO loginDao;
	
	public int join(MemberVO member) {
		
		return loginDao.join(member);
	}
	
	public MemberVO loginCheck(String userJSON) throws Exception{
		
		JSONParser parser = new JSONParser();

		JSONObject jsonObject = (JSONObject) parser.parse(userJSON);
		JSONObject dataObject = (JSONObject) jsonObject.get("data");
		
		String user = (String) dataObject.get("user");
		String pw = (String) dataObject.get("password");
		
		LoginVO loginVO = new LoginVO();
		
		loginVO.setUser(user);
		loginVO.setPassword(pw);
		
		System.out.println("id : " + user);
		System.out.println("pw : " + pw);
		
		return loginDao.loginCheck(loginVO);
	}
	
	public int loginAccessCount(int seq) {
		return loginDao.loginAccessCount(seq);
	}
	
	public LoginCountVO getTodayLoginCount(int seq) {
		return loginDao.getTodayLoginCount(seq);
	}
}
