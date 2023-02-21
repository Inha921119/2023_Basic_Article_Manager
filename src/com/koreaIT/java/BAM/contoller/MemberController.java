package com.koreaIT.java.BAM.contoller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.Util.Util;
import com.koreaIT.java.BAM.dto.Member;

public class MemberController extends Controller {
	List<Member> members;
	Scanner sc;
	int lastMemberId;
	
	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			System.out.println("실행 확인");
//			doLogin();
			break;
		case "logout":
			System.out.println("실행 확인");
//			doLogout();
			break;
		}
	}
	

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	}
	
	
	public void doJoin() {
		int id = lastMemberId + 1;
		lastMemberId = id;

		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginIdDupChk(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			} 
			
			if (loginId.equals("")) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}

		String loginPw = null;
		String loginPwChk = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.equals("")) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			System.out.printf("로그인 비밀번호 확인: ");
			loginPwChk = sc.nextLine().trim();

			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		
		String name = null;
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			if (name.equals("")) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			break;
		}

		String regDate = Util.getNowDateTime();
		
		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%s님 회원가입이 완료되었습니다.\n", loginId);
		lastMemberId++;
	}
	
	private boolean loginIdDupChk(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
}
