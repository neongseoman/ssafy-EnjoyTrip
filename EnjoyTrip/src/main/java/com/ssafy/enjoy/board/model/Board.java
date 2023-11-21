package com.ssafy.enjoy.board.model;

public class Board {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
	private String sessionId;

	public Board() {
		super();
		setArticleNo(0);
		setUserId(null);
		setSubject(null);
		setContent(null);
		setHit(0);
		setRegisterTime(null);
		setSessionId(null);
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		if(subject==null) {
			subject = "";
		}
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if(content==null) {
			content="";
		}
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		if(hit<0) {
			hit = 0;
		}
		this.hit = hit;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		if(registerTime==null) {
			registerTime = "";
		}
		this.registerTime = registerTime;
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
