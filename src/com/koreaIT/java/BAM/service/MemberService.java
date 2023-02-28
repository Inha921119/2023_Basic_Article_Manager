package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {

	public List<Member> getWriteMember() {
		return Container.memberDao.getWriteMember();
	}

}
