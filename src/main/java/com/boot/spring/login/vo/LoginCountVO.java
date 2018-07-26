package com.boot.spring.login.vo;

import java.sql.Date;

public class LoginCountVO {
	int member_seq;
	private String m_id;
	int cnt;
	String reg_date;
	
	
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getCount() {
		return cnt;
	}
	public void setCount(int cnt) {
		this.cnt = cnt;
	}
	public String getReg_data() {
		return reg_date;
	}
	public void setReg_data(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "LoginCountVO [member_seq=" + member_seq + ", m_id=" + m_id + ", cnt=" + cnt + ", reg_date=" + reg_date
				+ "]";
	}
	
	
	
}
