package com.ssafy.enjoy.session.model;

public class SessionReqModel {
    String userId;

    public SessionReqModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
