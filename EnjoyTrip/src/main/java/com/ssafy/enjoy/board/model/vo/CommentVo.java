package com.ssafy.enjoy.board.model.vo;

import com.ssafy.util.VOException;

public class CommentVo {
	private int idx;
	private String content;
	private String userName;
	private String userId;
	private int articleNo;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) throws VOException{
		if(idx<=0) {
			throw new VOException("idx is smaller than 1");
		}
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) throws VOException{
		if(content==null||"".equals(content)) {
			throw new VOException("content is empty");
		}
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) throws VOException {
		if(userName==null||"".equals(userName)) {
			throw new VOException("user name is empty");
		}
		this.userName = userName;
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
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws VOException {
		if(articleNo<=0) {
			throw new VOException("article number is smaller than 1");
		}
		this.articleNo = articleNo;
	}
}
