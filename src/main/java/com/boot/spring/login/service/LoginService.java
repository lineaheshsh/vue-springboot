package com.boot.spring.login.service;

import java.util.List;

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
		
		int success = loginDao.loginAccessCount(seq);
		System.out.println("[LoginService] loginAccessCount >> success = " + success);
		if ( success == 1 ) return success;
		else return 0;
	}
	
	public LoginCountVO getTotalTodayLoginCount() {
		return loginDao.getTotalTodayLoginCount();
	}
	
	public List<LoginCountVO> getTodayAccessRanking10() {
		return loginDao.getTodayAccessRanking10();
	}
	
	public List<LoginCountVO> getWeekLoginCount(int seq) {
		return loginDao.getWeekLoginCount(seq);
	}
}
