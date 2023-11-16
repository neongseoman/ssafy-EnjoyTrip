package com.ssafy.enjoy.board.model;

public class Comment {
	private int idx;
	private String content;
	private String user_name;
	private String usre_id;
	private int article_no;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsre_id() {
		return usre_id;
	}
	public void setUsre_id(String usre_id) {
		this.usre_id = usre_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
}
