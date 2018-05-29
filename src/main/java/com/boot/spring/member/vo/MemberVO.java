package com.boot.spring.member.vo;

import java.util.List;

import com.boot.spring.member.util.MemberRole;

public class MemberVO {
	private int    m_seq;
	private String m_id;
	private String m_email;
	private String m_pw;
	private String m_name;
	private char   m_gender;
	private String m_regdate;
	private String m_update;
	private List<MemberRole> roles;
	
	
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public char getM_gender() {
		return m_gender;
	}
	public void setM_gender(char m_gender) {
		this.m_gender = m_gender;
	}
	public String getM_regdata() {
		return m_regdate;
	}
	public void setM_regdata(String m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_update() {
		return m_update;
	}
	public void setM_update(String m_update) {
		this.m_update = m_update;
	}
	public List<MemberRole> getRoles() {
		return roles;
	}
	public void setRoles(List<MemberRole> roles) {
		this.roles = roles;
	}
}
