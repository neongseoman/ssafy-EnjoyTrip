package com.ssafy.enjoy.member.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class ResDto {
    String msg;
    String detail;

    public void setMsg(String msg) {
        if (msg == null){
            this.msg="";
        }
    }

    public void setDetail(String detail) {
        if (detail == null)
            this.detail = "";
    }

    public ResDto(String msg, String detail) {
        setDetail(msg);
        setMsg(detail);
    }

    @Override
    public String toString() {
        return "ResDto{" +
                "msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
