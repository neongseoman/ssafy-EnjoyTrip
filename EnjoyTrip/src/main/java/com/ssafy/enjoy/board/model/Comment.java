package com.ssafy.enjoy.board.model;

public class Comment {
	private int idx;
	private String content;
	private String userName;
	private String usreId;
	private int articleNo;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUsreId() {
		return usreId;
	}
	public void setUsreId(String usreId) {
		this.usreId = usreId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
}
