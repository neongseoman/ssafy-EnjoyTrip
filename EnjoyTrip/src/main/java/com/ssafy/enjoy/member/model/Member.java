package com.ssafy.enjoy.member.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
public class Member {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;

	public Member() {}


	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", emailId="
				+ emailId + ", emailDomain=" + emailDomain + ", joinDate="]";
	}
	
}
