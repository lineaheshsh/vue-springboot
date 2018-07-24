package com.boot.spring.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.spring.login.vo.LoginCountVO;
import com.boot.spring.login.vo.LoginVO;
import com.boot.spring.member.vo.MemberVO;

@Component
public class LoginDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public int join(MemberVO member) {
		
		System.out.println("[join] member password : " + member.getM_pw());
		
		return sqlSession.insert("join", member);
	}
	
	public MemberVO loginCheck(LoginVO login) {
		System.out.println("[loginCheck] login Info : " + login.toString());
		
		return sqlSession.selectOne("loginCheck", login);
	}
	
	public int loginAccessCount(int seq) {
		System.out.println("[loginAccessCount] seq Info : " + seq);
		int success = -1;
		try {
			success = sqlSession.insert("loginAccessCount", seq);
			System.out.println("[LoginDAO] loginAccessCount >> success = " + success);
			return success;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.getMessage());
			return 0;
		}
		
	}
	
	public LoginCountVO getTotalTodayLoginCount() {
		
		return sqlSession.selectOne("getTotalTodayLoginCount");
	}
	
	public List<LoginCountVO> getWeekLoginCount(int seq) {
		
		return sqlSession.selectList("getWeekLoginCount", seq);
	}
}
