package com.boot.spring.member.dao;

import java.util.List;
import java.util.Map;

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
	
	public int userUpdate(Map userInfo) {
		int success = -1;
		try {
			success = sqlSession.update("updateMember", userInfo);
			System.out.println("[MemberDAO] userUpdate >> success = " + success);
			return success;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.getMessage());
			return success;
		}
	}
	
	public int userInsert(MemberVO member) {
		int success = -1;
		try {
			success = sqlSession.update("insertMember", member);
			System.out.println("[MemberDAO] userInsert >> success = " + success);
			return success;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.getMessage());
			return success;
		}
	}
	
	public List<MemberVO> getUserList() {
		try {
			List<MemberVO> result = sqlSession.selectList("getMembers");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.getMessage());
			return null;
		}
	}
}
