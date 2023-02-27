package com.koreaIT.java.BAM.contoller;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	public abstract void doAction(String command, String actionMethodName);
	
	public static Member loginedMember = null;

	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void makeTestData();
}