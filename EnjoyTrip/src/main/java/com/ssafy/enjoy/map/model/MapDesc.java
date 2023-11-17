package com.ssafy.enjoy.map.model;

public class MapDesc {
	private int contentId;
	private String homepage, overview, telname;
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTelname() {
		return telname;
	}
	public void setTelname(String telname) {
		this.telname = telname;
	}
}
