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
                ", userName='" + userName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", hashedUserAgent='" + hashedUserAgent + '\'' +
                ", blackListPoint=" + blackListPoint +
                ", latelyAccessTime=" + latelyAccessTime +
                '}';
    }
}
