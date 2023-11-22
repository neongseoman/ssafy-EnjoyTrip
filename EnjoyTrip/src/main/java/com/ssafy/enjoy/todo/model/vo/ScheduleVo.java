package com.ssafy.enjoy.todo.model.vo;

import com.ssafy.util.Exception.VOException;

public class ScheduleVo {
	private String title, time, date, userId;
	private boolean completed;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) throws VOException {
		if(title==null||"".equals(title)) {
			throw new VOException("title is empty");
		}
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) throws VOException {
		if(time==null||"".equals(time)) {
			throw new VOException("time is empty");
		}
		if(!time.matches("^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$")&&!time.matches("([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$")) {
			throw new VOException("time is in wrong format");
		}
		this.time = time;
	}
	public String getDate() throws VOException {
		if(date==null||"".equals(date)) {
			throw new VOException("date is empty");
		}
		if(!date.matches("^d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
			throw new VOException("date is in wrong format");
		}
		return date;
	}
	public void setDate(String date) throws VOException {
		if(date==null||"".equals(date)) {
			throw new VOException( "date is empty");
		}
		if(!date.matches("^d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
			throw new VOException( "date is in wrong format");
		}
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) throws VOException {
		if(userId==null||"".equals(userId)) {
			throw new VOException("user id is empty");
		}
		this.userId = userId;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
