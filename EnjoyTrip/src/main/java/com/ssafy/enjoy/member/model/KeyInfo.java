package com.ssafy.enjoy.member.model;

public class KeyInfo {
	private String hashed_id;
	private String salt;
	private String key;
	@Override
	public String toString() {
		return "KeyInfo [hashed_id=" + hashed_id + ", salt=" + salt + ", key=" + key + "]";
	}
	public String getHashed_id() {
		return hashed_id;
	}
	public void setHashed_id(String hashed_id) {
		this.hashed_id = hashed_id;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
