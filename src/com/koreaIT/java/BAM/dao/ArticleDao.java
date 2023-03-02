package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;

public class ArticleDao extends Dao {
	public List<Article> articles;

	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
	public void remove(Article article) {
		articles.remove(article);
	}

	public List<Article> getPrintArticles(String searchKeyword) {

		if (searchKeyword.length() > 0) {
			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}
		return articles;
	}

	public String getShortTitle(String Title) {
		if (Title.length() > 5) {
			return Title.substring(0, 3) + "...";
		} else {
			return Title;
		}
	}

	public String getWriteMemberName(int id) {
		for (Member member : Container.memberDao.members) {
				if (id == member.id) {
					return member.name;
			}
		}
		return null;
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

}