package com.ssafy.enjoy.member.model.dto;

import lombok.Getter;

@Getter
public class MemberResDto extends ResDto{
    String sessionId;
    String name;
    String id;

    public MemberResDto(String msg, String detail, String sessionId, String name, String id) {
        super(msg, detail);
        this.sessionId = sessionId;
        this.name = name;
        this.id = id;
    }
}
