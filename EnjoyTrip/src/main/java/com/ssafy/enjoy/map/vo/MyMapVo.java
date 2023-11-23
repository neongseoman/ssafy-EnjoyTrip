package com.ssafy.enjoy.map.vo;

import static com.ssafy.enjoy.map.data.OfficialMapData.contentTypeIdList;
import static com.ssafy.enjoy.map.data.OfficialMapData.gugunCodeList;
import static com.ssafy.enjoy.map.data.OfficialMapData.gugunNameList;
import static com.ssafy.enjoy.map.data.OfficialMapData.sidoCodeList;
import static com.ssafy.enjoy.map.data.OfficialMapData.sidoNameList;

import com.ssafy.util.Exception.VOException;

import lombok.Getter;

@Getter
public class MyMapVo {
	private int sido_code;
	private String sido_name;
	private int gugun_code;
	private String gugun_name;
	private int content_type_id;
	private int content_id;
	private String title;
	private String first_image;
	private String addr1;
	private String addr2;
	private String zipcode;
	private double latitude;
	private double longitude;
	private String tel;

	public void setSido_code(int sido_code) throws VOException {
		if (!sidoCodeList.contains(sido_code))
			throw new VOException("sido_code");
		this.sido_code =sido_code;
	}

	public void setSido_name(String sido_name)  throws VOException{
		if (sido_name == null || "".equals(sido_name))
			throw new VOException("sido_name");
		if (!sidoNameList.contains(sido_name))
			throw new VOException("sido_name");
		this.sido_name = sido_name;
	}

	public void setGugun_code(int gugun_code) throws VOException {
		if (!gugunCodeList.contains(gugun_code))
			throw new VOException("gugun_code");
		this.gugun_code = gugun_code;
	}

	public void setGugun_name(String gugun_name) throws VOException {
		if (gugun_name == null || "".equals(gugun_name))
			throw new VOException("gugun_name is null");
		if (!gugunNameList.contains(gugun_name))
			throw new VOException("gugun_name");
		this.gugun_name = gugun_name;
	}

	public void setContent_type_id(int content_type_id) throws VOException {
		if (!contentTypeIdList.contains(content_type_id))
			throw new VOException("content_type_id");
		this.content_type_id = content_type_id;
	}

	public void setContent_id(int content_id) throws VOException {
		if (content_id == 0)
			throw new VOException("content_id");
		this.content_id = content_id;
	}

	public void setTitle(String title) throws VOException {
		if (title == null || "".equals(title))
			throw new VOException("title");
		this.title = title;
	}

	public void setFirst_image(String first_image) throws VOException {
		if (first_image == null || "".equals(zipcode))
			throw new VOException("first_image");
		this.first_image = first_image;
	}

	public void setAddr1(String addr1) throws VOException {
		if (addr1 == null || "".equals(addr1))
			throw new VOException("addr1");
		this.addr1 = addr1;
	}

	public void setAddr2(String addr2) throws VOException {
		if (addr2 == null)
			throw new VOException("addr2");
		this.addr2 = addr2;
	}

	public void setZipcode(String zipcode) throws VOException {
		if (zipcode == null || "".equals(zipcode))
			throw new VOException("zipcode");
		this.zipcode = zipcode;
	}

	public void setLatitude(double latitude) throws VOException {
		if(latitude<-90.0||latitude>90.0) {
			throw new VOException("latitude is out of range");
		}
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) throws VOException {
		if(longitude<-180||longitude>180) {
			throw new VOException("longitude is out of range");
		}
		this.longitude = longitude;
	}

	public void setTel(String tel) throws VOException {
		if (tel == null)
			throw new VOException("tel");
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "MyMapVO{" +
				"sido_code=" + sido_code +
				", sido_name='" + sido_name + '\'' +
				", gugun_code=" + gugun_code +
				", gugun_name='" + gugun_name + '\'' +
				", content_type_id=" + content_type_id +
				", content_id=" + content_id +
				", title='" + title + '\'' +
				", first_image='" + first_image + '\'' +
				", addr1='" + addr1 + '\'' +
				", addr2='" + addr2 + '\'' +
				", zipcode='" + zipcode + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", tel='" + tel;
	}
}
