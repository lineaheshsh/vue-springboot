package com.boot.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.spring.member.vo.MemberVO;

@Component
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberVO> getMembers() {
		
		return null;
	}
	
	public MemberVO getMember(String id) {
		
		return this.sqlSession.selectOne("getMember", id);
	}
}
