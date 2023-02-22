package com.koreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int viewcount;
	public String writer;
	public String LastModifyDate;
	
	public Article(int id, String regDate, String writer, String title, String body){
		this(id, regDate, writer, title, body, 0);
	}
	
	public Article(int id, String regDate, String writer, String title, String body, int viewcount) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.viewcount = viewcount;
		this.writer = writer;
	}

	public void increseViewCount() {
		viewcount++;
	}
}