package com.ssafy.enjoy.member.model.dto;

import lombok.Getter;

@Getter
public abstract class ResDto {
    String msg;
    String detail;

    public void setMsg(String msg) {
        if (msg == null){
            this.msg="";
        }
        this.msg = msg;
    }

    public void setDetail(String detail) {
        if (detail == null)
            this.detail = "";
        this.detail = detail;
    }

    public ResDto(String msg, String detail) {
        setDetail(detail);
        setMsg(msg);
    }

    @Override
    public String toString() {
        return "ResDto{" +
                "msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
