package com.boot.spring.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
