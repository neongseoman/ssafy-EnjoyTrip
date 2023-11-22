package com.ssafy.enjoy.session.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SessionModel {
    //
    private String userId;
    private String userName;
    private String sessionId;
    private String hashedUserAgent;
    private int blackListPoint;
    private LocalDateTime latelyAccessTime;

    public SessionModel(String userId, String userName, String sessionId, String hashedUserAgent, int blackListPoint, LocalDateTime latelyAccessTime) {
        this.userId = userId;
        this.userName = userName;
        this.sessionId = sessionId;
        this.hashedUserAgent = hashedUserAgent;
        this.blackListPoint = blackListPoint;
        this.latelyAccessTime = latelyAccessTime;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHashedUserAgent(String hashedUserAgent) {
        this.hashedUserAgent = hashedUserAgent;
    }

    public void setBlackListPoint(int blackListPoint) {
        this.blackListPoint = blackListPoint;
    }

    public void plusOneBlackListPoint() {
        this.blackListPoint++;
    }

    public void setLatelyAccessTime(LocalDateTime latelyAccessTime) {
        this.latelyAccessTime = latelyAccessTime;
    }

    @Override
    public String toString() {
        return "SessionModel{" +
                "userId='" + userId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
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
