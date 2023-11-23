package com.ssafy.enjoy.member.model.dto;

public class ModifyMemberDto extends MemberDto {
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		if (newPassword.length() < 8){
			this.newPassword = "";
		}
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ModifyMember [nPassword=" + newPassword + "]" + super.toString();
	}
}
