package com.boot.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
			throw new UsernameNotFoundException("login fail");
		}
		
		return memberDao.getMember(id);
	}
}
