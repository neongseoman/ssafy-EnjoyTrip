package com.ssafy.enjoy.member.model;

import java.sql.Time;
import java.util.Date;

public class LoginTry {
	private String userId;
	private String clientIp;
	private Date lastTryDate;
	private Time lastTryTime;
	private int retry;
	
	
	public int getRetry() {
		return retry;
	}
	public void setRetry(int retry) {
		this.retry = retry;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Date getLastTryDate() {
		return lastTryDate;
	}
	public void setLastTryDate(Date lastTryDate) {
		this.lastTryDate = lastTryDate;
	}
	public Time getLastTryTime() {
		return lastTryTime;
	}
	public void setLastTryTime(Time lastTryTime) {
		this.lastTryTime = lastTryTime;
	}
	
}
