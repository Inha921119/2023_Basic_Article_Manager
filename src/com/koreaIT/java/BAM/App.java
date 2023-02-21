package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.Util.Util;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;

public class App {
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;
		int lastMemberId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해 주세요.");
				continue;
			}
			if (command.equals("exit")) {
				break;
			}

			if (command.equals("member join")) {
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
				continue;
			}

			if (command.startsWith("article list")) {
				if (lastArticleId == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				String searchKeyword = command.substring("article list".length()).trim();

				List<Article> printArticles = new ArrayList<>(articles);

				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);

					printArticles.clear();

					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}

				}

				System.out.println("|번호		 |제목		|날짜		|조회수		");
				Collections.reverse(printArticles);
				for (Article article : printArticles) {
					System.out.printf("|%d		 |%s		|%s	|%d		\n", article.id, article.title,
							article.regDate.substring(0, 10), article.viewcount);
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();

				String regDate = Util.getNowDateTime();

				Article article = new Article(id, regDate, title, body);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if (command.startsWith("article detail")) {
				if (command.split(" ").length == 2) {
					System.out.println("detail 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("detail 뒤에 숫자만 입력해주세요");
					continue;
				}

				String cmdBits = command.split(" ")[2];
				int id = Integer.parseInt(cmdBits);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				foundArticle.increseViewCount();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				if (foundArticle.LastModifyDate != null) {
					System.out.printf("수정된 날짜 : %s\n", foundArticle.LastModifyDate);
				}
				System.out.printf("조회수 : %d회\n", foundArticle.viewcount);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (command.startsWith("article delete")) {
				if (command.split(" ").length == 2) {
					System.out.println("delete 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("delete 뒤에 숫자만 입력해주세요");
					continue;
				}

				String cmdBits = command.split(" ")[2];
				int id = Integer.parseInt(cmdBits);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				articles.remove(articles.indexOf(foundArticle));

				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
			} else if (command.startsWith("article modify")) {
				if (command.split(" ").length == 2) {
					System.out.println("modify 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("modify 뒤에 숫자만 입력해주세요");
					continue;
				}

				String cmdBits = command.split(" ")[2];
				int id = Integer.parseInt(cmdBits);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("%d번 게시물의 '제목'과 '내용'중 무엇을 수정하시겠습니까?\n", id);
				String command_modify = sc.nextLine().trim();

				if (command_modify.equals("제목")) {
					System.out.printf("%d번 게시물의 제목을 수정합니다\n", id);
					System.out.printf("제목 : ");
					String title = sc.nextLine();

					foundArticle.title = title;

					String LastModifyDate = Util.getNowDateTime();

					foundArticle.LastModifyDate = LastModifyDate;

					System.out.printf("%d번 게시물의 제목이 수정되었습니다\n", id);

				} else if (command_modify.equals("내용")) {
					System.out.printf("%d번 게시물의 내용을 수정합니다\n", id);
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					foundArticle.body = body;

					String LastModifyDate = Util.getNowDateTime();
					foundArticle.LastModifyDate = LastModifyDate;

					System.out.printf("%d번 게시물의 내용이 수정되었습니다\n", id);
				} else {
					System.out.println("'제목' 혹은 '내용'을 입력해주세요");
				}

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}

	private boolean loginIdDupChk(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다");
		articles.add(new Article(1, Util.getNowDateTime(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getNowDateTime(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getNowDateTime(), "제목3", "내용3", 30));
	}
}
