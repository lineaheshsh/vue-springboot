package com.boot.spring.login.vo;

import java.sql.Date;

public class LoginCountVO {
	int member_seq;
	int cnt;
	long reg_data;
	
	
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public int getCount() {
		return cnt;
	}
	public void setCount(int cnt) {
		this.cnt = cnt;
	}
	public long getReg_data() {
		return reg_data;
	}
	public void setReg_data(long reg_data) {
		this.reg_data = reg_data;
	}
	
	
}
