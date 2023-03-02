package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	public void add(Article article) {
		Container.articleDao.add(article);
	}
	public void remove(Article article) {
		Container.articleDao.remove(article);
	}
	
	public List<Article> getPrintArticles(String searchKeyword) {
		return Container.articleDao.getPrintArticles(searchKeyword);
	}

	public String getShortTitle(String Title) {
		return Container.articleDao.getShortTitle(Title);
	}
	
	public Article getArticleById(int id) {
		return Container.articleDao.getArticleById(id);
	}
	public int getLastId() {
		return Container.articleDao.getLastId();
	}
	public void articleModifyTitle(Article foundArticle, String title, String lastModifyDate) {
		Container.articleDao.articleModifyTitle(foundArticle, title, lastModifyDate);
	}
	public void articleModifyBody(Article foundArticle, String body, String lastModifyDate) {
		Container.articleDao.articleModifyBody(foundArticle, body, lastModifyDate);
	}
}