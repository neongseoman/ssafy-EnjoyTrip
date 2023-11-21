package com.ssafy.enjoy.board.model;

public class Comment {
	private int idx;
	private String content;
	private String userName;
	private String userId;
	private int articleNo;
	private String sessionId;
	public int getIdx() {
		return idx;
	}
	public Comment() {
		super();
		setIdx(0);
		setContent(null);
		setUserName(null);
		setUserId(null);
		setArticleNo(0);
		setSessionId(null);
	}
	public void setIdx(int idx) {
		if(idx<0) {
			idx = 0;
		}
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if(content==null) {
			content = "";
		}
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName==null) {
			userName = "";
		}
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		if(userId==null) {
			userName = "";
		}
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		if(articleNo<0) {
			articleNo = 0;
		}
		this.articleNo = articleNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		if(sessionId==null) {
			sessionId = "";
		}
		this.sessionId = sessionId;
	}
}
