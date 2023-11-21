package com.ssafy.enjoy.session.model;

public class SessionReqDto {
    String userId;

    public SessionReqDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
