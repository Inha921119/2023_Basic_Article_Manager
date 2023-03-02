package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public void add(Member member) {
		memberDao.add(member);
	}
	public void remove(Member member) {
		memberDao.remove(member);
	}
	public boolean loginIdDupChk(String loginId) {
		return memberDao.loginIdDupChk(loginId);
	}
	
	public boolean mobileNumDupChk(String mobileNum) {
		return memberDao.mobileNumDupChk(mobileNum);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public String getWriterName(int id) {
		return memberDao.getWriterName(id);
	}
	public int getLastId() {
		return memberDao.getLastId();
	}
	public Member getSearchMemberId(String searchKeyword) {
		return memberDao.getSearchMemberId(searchKeyword);
	}
}