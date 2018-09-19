package com.boot.spring.member.vo;

import java.util.List;

import com.boot.spring.member.util.MemberRole;

public class MemberVO {
	private int    m_seq;
	private String m_id;
	private String m_email;
	private String m_pw;
	private String m_name;
	private String   m_gender;
	private String m_regdate;
	private String m_update;
	private String m_birthday;
	private String m_nation;
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
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
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
	public String getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_birthday() {
		return m_birthday;
	}
	public void setM_birthday(String m_birthday) {
		this.m_birthday = m_birthday;
	}
	public String getM_nation() {
		return m_nation;
	}
	public void setM_nation(String m_nation) {
		this.m_nation = m_nation;
	}
	public List<MemberRole> getRoles() {
		return roles;
	}
	public void setRoles(List<MemberRole> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "MemberVO [m_seq=" + m_seq + ", m_id=" + m_id + ", m_email=" + m_email + ", m_pw=" + m_pw + ", m_name="
				+ m_name + ", m_gender=" + m_gender + ", m_regdate=" + m_regdate + ", m_update=" + m_update
				+ ", m_birthday=" + m_birthday + ", m_nation=" + m_nation + ", roles=" + roles + "]";
	}
	
	
}
