package com.koreaIT.java.BAM.contoller;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	public static List<Member> members = new ArrayList<>();
	
	public abstract void doAction(String command, String actionMethodName);
	
	public static Member loginedMember = null;

	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void makeTestData();
}