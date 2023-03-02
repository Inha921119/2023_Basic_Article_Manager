package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public void add(Article article) {
		articleDao.add(article);
	}
	public void remove(Article article) {
		articleDao.remove(article);
	}
	
	public List<Article> getPrintArticles(String searchKeyword) {
		return articleDao.getPrintArticles(searchKeyword);
	}

	public String getShortTitle(String Title) {
		return articleDao.getShortTitle(Title);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	public int getLastId() {
		return articleDao.getLastId();
	}
	public void articleModifyTitle(Article foundArticle, String title, String lastModifyDate) {
		articleDao.articleModifyTitle(foundArticle, title, lastModifyDate);
	}
	public void articleModifyBody(Article foundArticle, String body, String lastModifyDate) {
		articleDao.articleModifyBody(foundArticle, body, lastModifyDate);
	}
}