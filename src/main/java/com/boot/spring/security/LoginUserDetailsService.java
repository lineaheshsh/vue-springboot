package com.boot.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.spring.member.service.MemberService;
import com.boot.spring.member.vo.MemberVO;

@Service
public class LoginUserDetailsService implements UserDetailsService
{
	@Autowired
	MemberService memberService;
	
	// 필자의 경우 기존 해시 시스템이 좀 다르기 때문에
	// 이부분을 적용한다면 암호가 구형시스템이라면 바꿔주는 부분이 필요.
	// @Autowired
	// private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException
	{
		// 기존해시와 신규해시가 다를경우 이런식으로 받아 처리할 수 있음.
		// 위 @Autowired HttpServletRequest request;
		// request 처리
		
		MemberVO member = memberService.getMember(id);
		
		if (member == null)
		{
			// 계정이 존재하지 않음
			throw new UsernameNotFoundException("login fail");
		}
		
		System.out.println("member ID : " + member.getM_id());
		
		return new LoginUserDetails(member);
	}
}
