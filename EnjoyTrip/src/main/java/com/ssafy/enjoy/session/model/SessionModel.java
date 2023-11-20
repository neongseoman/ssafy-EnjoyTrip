package com.ssafy.enjoy.session.model;

import java.util.Date;
import java.util.List;

public class SessionModel {
    //
    private String userId;
    private String sessionId;

    public SessionModel(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    //    private String userAgent;
//    private Date lastAccessTime; // 자바에서 찍는 시간의 포멧을 맞출 필요가 있음.
//
//    public String getUserAgent() {
//        return userAgent;
//    }
//
//    public void setUserAgent(String userAgent) {
//        this.userAgent = userAgent;
//    }
//
//    public Date getLastAccessTime() {
//        return lastAccessTime;
//    }
//
//    public void setLastAccessTime(Date lastAccessTime) {
//        this.lastAccessTime = lastAccessTime;
//    }

    // 웹 헤더에 브라우저나 디바이스 정보가 날아오긴 했는데... 흠... 이걸 갱신하는 것도 일이긴하네
    // 이걸 관리하려면 로그인 로직이 좀 더 복잡해질 수 있음.
//    private List<String> deviceId;
}
