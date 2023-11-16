package com.ssafy.enjoy.member.model;

public class IdInfo {
	private String id;
	private String salt;
	@Override
	public String toString() {
		return "InfoDto [id=" + id + ", salt=" + salt + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
