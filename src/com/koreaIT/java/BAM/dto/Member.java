package com.koreaIT.java.BAM.dto;

public class Member {
	public int id;
	public String regDate;
	public String loginId;
	public String loginPW;
	public String name;
	public String callnumber;

	public Member(int id, String regDate, String loginId, String loginPW, String name, String callnumber) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPW = loginPW;
		this.name = name;
		this.callnumber = callnumber;
	}
}