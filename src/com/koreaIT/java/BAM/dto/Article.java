package com.koreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int viewcount;
	public int writerId;
	public String LastModifyDate;
	
	public Article(int id, String regDate, int writerId, String title, String body){
		this(id, regDate, writerId, title, body, 0);
	}
	
	public Article(int id, String regDate, int writerId, String title, String body, int viewcount) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.viewcount = viewcount;
		this.writerId = writerId;
	}

	public void increseViewCount() {
		viewcount++;
	}
}