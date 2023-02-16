package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

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
			if (command.equals("article list")) {
				if (lastArticleId == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("|번호		 |제목		|날짜		");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("|%d		 |%s		|%s		\n", article.id, article.title, article.regDate.substring(0, 10));
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
// 여기부터
				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
// 여기까지 -> id로 게시물 검색 로직 
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n",foundArticle.regDate);
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

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				articles.remove(foundIndex);

				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;
	String regDate;
	String regTime;

	Article(int id,  String regDate, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}