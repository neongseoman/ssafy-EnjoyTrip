package com.ssafy.enjoy.board.model.dto;

import com.ssafy.util.DtoException;

public class PositionDto {
	private int articleNo;
	private double latitude;
	private double longitude;
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) throws DtoException {
		if(articleNo<=0) {
			throw new DtoException(PositionDto.class, "article number is smaller than 1");
		}
		this.articleNo = articleNo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) throws DtoException {
		if(latitude<-90.0||latitude>90.0) {
			throw new DtoException(PositionDto.class, "latitude is out of range");
		}
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) throws DtoException {
		if(longitude<-180||longitude>180) {
			throw new DtoException(PositionDto.class, "longitude is out of range");
		}
		this.longitude = longitude;
	}
}
