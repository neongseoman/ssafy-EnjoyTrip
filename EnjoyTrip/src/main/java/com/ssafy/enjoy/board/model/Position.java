package com.ssafy.enjoy.board.model;

public class Position {
	private int articleNo;
	private double latitude;
	private double longitude;
	private int idx;
	private String sessionId;
	@Override
	public String toString() {
		return "Position [articleNo=" + articleNo + ", latitude=" + latitude + ", longitude=" + longitude + ", idx="
				+ idx + "]";
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
