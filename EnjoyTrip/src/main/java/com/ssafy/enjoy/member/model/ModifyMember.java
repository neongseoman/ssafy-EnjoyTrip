package com.ssafy.enjoy.member.model;

public class ModifyMember extends Member {
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ModifyMember [nPassword=" + newPassword + "]" + super.toString();
	}
}
