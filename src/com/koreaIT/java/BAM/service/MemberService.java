package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
	public void add(Member member) {
		Container.memberDao.add(member);
	}
	public void remove(Member member) {
		Container.memberDao.remove(member);
	}
	public boolean loginIdDupChk(String loginId) {
		return Container.memberDao.loginIdDupChk(loginId);
	}
	
	public boolean mobileNumDupChk(String mobileNum) {
		return Container.memberDao.mobileNumDupChk(mobileNum);
	}

	public Member getMemberByLoginId(String loginId) {
		return Container.memberDao.getMemberByLoginId(loginId);
	}
	
	public String getWriterName(int id) {
		return Container.memberDao.getWriterName(id);
	}
	public int getLastId() {
		return Container.memberDao.getLastId();
	}
	public Member getSearchMemberId(String searchKeyword) {
		return Container.memberDao.getSearchMemberId(searchKeyword);
	}
}