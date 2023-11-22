package com.ssafy.enjoy.member.model.vo;

import com.ssafy.util.Exception.VOException;
import lombok.Getter;

@Getter
public class MemberVo {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	public void setUserId(String userId) throws VOException{
		if(userId==null||"".equals(userId))
			throw new VOException("usreId is empty");
		this.userId = userId;
	}
	public void setUserName(String userName) throws VOException{
		if(userName==null||"".equals(userName))
			throw new VOException("userName is empty");
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) throws VOException{
		if(userPassword==null||"".equals(userPassword)||userPassword.length()!=64) {
			throw new VOException("usrePassword is empty");
		}
		this.userPassword = userPassword;
	}
	public void setEmailId(String emailId) throws VOException{
		if(emailId==null||"".equals(emailId)) {
			throw new VOException("email id is empty");
		}
		this.emailId = emailId;
	}
	public void setEmailDomain(String emailDomain)throws VOException{
		if(emailDomain==null||"".equals(emailDomain)) {
			throw new VOException("email domain is empty");
		}

		if(!emailDomain.matches("^[a-zA-Z0-9.-]+\\.com$")) {
			throw new VOException("email domain is in wrong form");
		}
		this.emailDomain = emailDomain;
	}
//	public boolean setEmailDomain(String emailDomain)throws VOException{
//		if(emailDomain==null||"".equals(emailDomain)) {
//			return false;
////			throw new VOException("email domain is empty");
//		}
//
//		if(emailDomain.matches("^[a-zA-Z0-9.-]+\\.com$")) {
//			return false;
////			throw new VOException("email domain is in wrong form");
//		}
//		return true;
////		this.emailDomain = emailDomain;
//	}
	public void setJoinDate(String joinDate)throws VOException {
		if(joinDate==null||"".equals(joinDate)) {
			throw new VOException("join date is empty");
		}
		if(!joinDate.matches("^d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$")) {
			throw new VOException("join date is in wrong form");
		}
		this.joinDate = joinDate;
	}
}
