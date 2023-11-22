package com.ssafy.enjoy.board.model.vo;

import com.ssafy.util.VOException;

public class PositionVo {
	private int articleNo;
	private double latitude;
	private double longitude;
	private int idx;
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws VOException {
		if(articleNo<=0) {
			throw new VOException("article number is smaller than 1");
		}
		this.articleNo = articleNo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) throws VOException {
		if(latitude<-90.0||latitude>90.0) {
			throw new VOException("latitude is out of range");
		}
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) throws VOException {
		if(longitude<-180||longitude>180) {
			throw new VOException("longitude is out of range");
		}
		this.longitude = longitude;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) throws VOException {
		if(idx<=0) {
			throw new VOException("idx is smaller than 1");
		}
		this.idx = idx;
	}
}
