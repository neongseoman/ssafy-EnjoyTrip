package com.ssafy.enjoy.member.model.dto;

import com.ssafy.util.DtoException;

import lombok.Getter;

@Getter
public class MemberDto {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;

	public MemberDto() {}

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
<<<<<<< HEAD
//
=======

>>>>>>> 6c06b0b5b8e714b3e6b0bfcb5087e0cb2e4328bf
	public void setUserPassword(String userPassword) throws DtoException {
		if (userId == null || "".equals(userId))
			throw new DtoException(MemberDto.class, "user id");
		this.userPassword = userPassword;
	}
	public void setEmailId(String emailId) throws DtoException {
		if (userId == null || "".equals(userId))
			throw new DtoException(MemberDto.class, "emailId");
		this.emailId = emailId;
	}

	public void setEmailDomain(String emailDomain) throws DtoException {
		if (userId == null || "".equals(userId))
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
