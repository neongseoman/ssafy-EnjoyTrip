package com.ssafy.enjoy.member.model.dto;

import com.ssafy.enjoy.member.model.dto.MemberDto;

public class ModifyMemberDto extends MemberDto {
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		if (newPassword.length() < 8){
			this.newPassword = "";
		}
	}

	public ModifyMemberDto(String newPassword) {
		setNewPassword(newPassword);
	}

	@Override
	public String toString() {
		return "ModifyMember [nPassword=" + newPassword + "]" + super.toString();
	}
}