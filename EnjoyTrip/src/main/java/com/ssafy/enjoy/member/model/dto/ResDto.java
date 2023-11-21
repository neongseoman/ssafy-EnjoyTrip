package com.ssafy.enjoy.member.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class ResDto {
    String msg;

    String detail;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ResDto(String msg, String detail) {
        this.msg = msg;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ResDto{" +
                "msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
