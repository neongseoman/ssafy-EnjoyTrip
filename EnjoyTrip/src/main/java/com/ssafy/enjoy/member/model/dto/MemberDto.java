package com.ssafy.enjoy.member.model.dto;

import com.ssafy.util.Exception.DtoException;

import lombok.Getter;

@Getter
public class MemberDto {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;


	public void setUserId(String userId) throws  DtoException {
		if (userId == null || "".equals(userId))
			throw new DtoException(MemberDto.class, "user id");
		this.userId = userId;
	}

	public void setUserName(String userName) throws DtoException {
		if (userName == null || "".equals(userName))
			throw new DtoException(MemberDto.class, "user name");
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) throws DtoException {
		if (userPassword == null || "".equals(userPassword))
			throw new DtoException(MemberDto.class, "user password");
		this.userPassword = userPassword;
	}
	public void setEmailId(String emailId) throws DtoException {
		if (emailId == null || "".equals(emailId))
			throw new DtoException(MemberDto.class, "emailId");
		this.emailId = emailId;
	}

	public void setEmailDomain(String emailDomain) throws DtoException {
		if (emailDomain == null || "".equals(emailDomain))
			throw new DtoException(MemberDto.class, "emailDomain");
		this.emailDomain = emailDomain;
	}

	@Override
	public String toString() {
		return "MemberDto{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", userPassword='" + userPassword + '\'' +
				", emailId='" + emailId + '\'' +
				", emailDomain='" + emailDomain + '\'' +
				'}';
	}
}
