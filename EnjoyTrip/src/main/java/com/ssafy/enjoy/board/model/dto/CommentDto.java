package com.ssafy.enjoy.board.model.dto;

import com.ssafy.util.Exception.DtoException;

public class CommentDto {
	private String content;
	private String userName;
	private String userId;
	private int articleNo;
	private int idx;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) throws DtoException {
		if(idx<=0) {
			throw new DtoException(CommentDto.class, "idx is smaller than 1");
		}
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) throws DtoException {
		if(content==null||"".equals(content)) {
			throw new DtoException(CommentDto.class, "content is empty");
		}
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) throws DtoException {
		if(userName==null||"".equals(userName)) {
			throw new DtoException(CommentDto.class, "user name is empty");
		}
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) throws DtoException {
		if(userId==null||"".equals(userId)) {
			throw new DtoException(CommentDto.class, "user id is empty");
		}
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws DtoException {
		if(articleNo<=0) {
			throw new DtoException(CommentDto.class, "article number is smaller than 1");
		}
		this.articleNo = articleNo;
	}
	
}
