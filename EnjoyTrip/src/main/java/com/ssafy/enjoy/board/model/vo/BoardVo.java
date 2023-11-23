package com.ssafy.enjoy.board.model.vo;

import com.ssafy.util.Exception.VOException;

public class BoardVo {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
	
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws VOException {
		if(articleNo<=0) {
			throw new VOException("article number is empty");
		}
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) throws VOException {
		if(userId==null||"".equals(userId)) {
			throw new VOException("user id is empty");
		}
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) throws VOException {
		if(subject==null||"".equals(subject)) {
			throw new VOException("subject is empty");
		}
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) throws VOException {
		if(content==null||"".equals(content)) {
			throw new VOException("content is empty");
		}
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) throws VOException {
		if(hit<0) {
			throw new VOException("hit is smaller than 0");
		}
		this.hit = hit;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) throws VOException {
		if(registerTime==null||"".equals(registerTime)) {
			throw new VOException("register time is empty");
		}
		if(!registerTime.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$")) {
			throw new VOException("register time is in wrong form");
		}
		this.registerTime = registerTime;
	}
	
}
