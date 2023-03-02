package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public MemberDao() {
		this.members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}
	
	public void remove(Member member) {
		members.remove(member);
	}

	public boolean loginIdDupChk(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public boolean mobileNumDupChk(String mobileNum) {
		for (Member member : members) {
			if (member.mobileNum.equals(mobileNum)) {
				return false;
			}
		}
		return true;
	}

	public Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Member getSearchMemberId(String searchKeyword) {
		for (Member member : members) {
			if (searchKeyword.equals(member.loginId)) {
				return member;
			}
		}
		return null;
	}
}