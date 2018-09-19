package com.boot.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.member.dao.MemberDAO;
import com.boot.spring.member.vo.MemberVO;

@Service("memberService")
public class MemberService {

	@Autowired
	MemberDAO memberDao;
	
	public MemberVO getMember(String id) {
		
		if ( id.equals("") || id == null ) {
			
			System.out.println("[getMember(String id)] ID parameter is null");
//			throw new UsernameNotFoundException("login fail");
		}
		
		return memberDao.getMember(id);
	}
	
	public int userUpdate(Map userInfo) {
		
		return memberDao.userUpdate(userInfo);
	}
	
	public int userInsert(MemberVO member) {
		
		return memberDao.userInsert(member);
	}
	
	public List<MemberVO> getUserList() {
		
		return memberDao.getUserList();
	}
}
