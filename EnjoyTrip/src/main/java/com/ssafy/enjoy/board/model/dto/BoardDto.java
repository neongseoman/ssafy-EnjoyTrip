package com.ssafy.enjoy.board.model.dto;

import com.ssafy.util.DtoException;

public class BoardDto {
	private int articleNo;
	private String userId;
	private String subject;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) throws DtoException {
		if(userId==null || "".equals(userId)) {
			throw new DtoException(BoardDto.class,"user id is empty");
		}
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) throws DtoException {
		if(subject==null||"".equals(subject)) {
			throw new DtoException(BoardDto.class, "subject is empty");
		}
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) throws DtoException {
		if(content==null||"".equals(content)) {
			throw new DtoException(BoardDto.class, "content is empty");
		}
		this.content = content;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws DtoException {
		if(articleNo<=0) {
			throw new DtoException(BoardDto.class, "article no is smaller than 1");
		}
		this.articleNo = articleNo;
	}
	private String content;
}
