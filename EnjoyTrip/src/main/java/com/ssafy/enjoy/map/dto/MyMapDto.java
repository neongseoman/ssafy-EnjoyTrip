package com.ssafy.enjoy.map.dto;

import com.ssafy.util.DtoException;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ssafy.enjoy.map.data.OfficialMapData.*;

@Getter
public class MyMapDto {
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
    private String sessionId;

    public void setSido_code(int sido_code) throws DtoException {
        if (!sidoCodeList.contains(sido_code))
            throw new DtoException(MyMapDto.class,"sido_code");
        this.sido_code =sido_code;
    }

    public void setSido_name(String sido_name)  throws DtoException{
        if (!sidoNameList.contains(sido_name))
            throw new DtoException(MyMapDto.class,"sido_name");
        this.sido_name = sido_name;
    }

    public void setGugun_code(int gugun_code) throws DtoException {
        if (!gugunCodeList.contains(gugun_code))
            throw new DtoException(MyMapDto.class,"gugun_code");
        this.gugun_code = gugun_code;
    }

    public void setGugun_name(String gugun_name) throws DtoException {
        if (!gugunNameList.contains(gugun_name))
            throw new DtoException(MyMapDto.class,"gugun_name");
        this.gugun_name = gugun_name;
    }

    public void setContent_type_id(int content_type_id) throws DtoException {
        if (!contentTypeIdList.contains(gugun_name))
            throw new DtoException(MyMapDto.class,"content_type_id");
        this.content_type_id = content_type_id;
    }

    public void setContent_id(int content_id) throws DtoException {
        if (content_id == 0)
            throw new DtoException(MyMapDto.class,"content_id");
        this.content_id = content_id;
    }

    public void setTitle(String title) throws DtoException {
        if (title == null)
            throw new DtoException(MyMapDto.class,"title");
        this.title = title;
    }

    public void setFirst_image(String first_image) {
        this.first_image = first_image;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "MyMapDto{" +
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
                ", tel='" + tel + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
