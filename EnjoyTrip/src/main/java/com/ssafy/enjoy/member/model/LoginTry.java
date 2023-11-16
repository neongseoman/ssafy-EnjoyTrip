package com.ssafy.enjoy.member.model;

import java.sql.Time;
import java.util.Date;

public class LoginTry {
	private String user_id;
	private String client_ip;
	private Date last_try_date;
	private Time last_try_time;
	private int retry;
	
	
	public int getRetry() {
		return retry;
	}
	public void setRetry(int retry) {
		this.retry = retry;
	}
	@Override
	public String toString() {
		return "LoginTry [user_id=" + user_id + ", client_ip=" + client_ip + ", last_try_date=" + last_try_date
				+ ", last_try_time=" + last_try_time + ", retry=" + retry + "]";
	}
	public Date getLast_try_date() {
		return last_try_date;
	}
	public void setLast_try_date(Date last_try_date) {
		this.last_try_date = last_try_date;
	}
	public Time getLast_try_time() {
		return last_try_time;
	}
	public void setLast_try_time(Time last_try_time) {
		this.last_try_time = last_try_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
}
