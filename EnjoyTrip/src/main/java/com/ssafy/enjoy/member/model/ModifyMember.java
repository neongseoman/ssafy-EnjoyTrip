package com.ssafy.enjoy.member.model;

public class ModifyMember extends Member {
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		if (newPassword.length() < 8){
			this.newPassword = "";
		}
	}

	public ModifyMember(String newPassword) {
		setNewPassword(newPassword);
	}

	@Override
	public String toString() {
		return "ModifyMember [nPassword=" + newPassword + "]" + super.toString();
	}
}
