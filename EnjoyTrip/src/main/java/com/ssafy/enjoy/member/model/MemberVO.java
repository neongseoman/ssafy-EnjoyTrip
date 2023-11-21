package com.ssafy.enjoy.member.model;

import com.ssafy.util.VOException;

public class MemberVO {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) throws VOException{
		if(userId==null||"".equals(userId))
			throw new VOException("usreId is empty");
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) throws VOException{
		if(userName==null||"".equals(userName))
			throw new VOException("userName is empty");
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) throws VOException{
		if(userPassword==null||"".equals(userPassword)) {
			throw new VOException("usrePassword is empty");
		}
		this.userPassword = userPassword;
	}
	public String getEmailId(){
		return emailId;
	}
	public void setEmailId(String emailId) throws VOException{
		if(emailId==null||"".equals(emailId)) {
			throw new VOException("email id is empty");
		}
		this.emailId = emailId;
	}
	public String getEmailDomain(){
		return emailDomain;
	}
	public void setEmailDomain(String emailDomain)throws VOException{
		if(emailDomain==null||"".equals(emailDomain)) {
			throw new VOException("email domain is empty");
		}
		if(!emailDomain.contains(".")||!emailDomain.matches("com$")) {
			throw new VOException("email domain is in wrong form");
		}
		this.emailDomain = emailDomain;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate)throws VOException {
		if(joinDate==null||"".equals(joinDate)) {
			throw new VOException("join date is empty");
		}
		if(!joinDate.matches("^d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]")) {
			throw new VOException("join date is in wrong form");
		}
		this.joinDate = joinDate;
	}
}
