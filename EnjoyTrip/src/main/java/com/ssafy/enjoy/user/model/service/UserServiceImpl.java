package com.ssafy.enjoy.user.model.service;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.mapper.MemberMapper;
import com.ssafy.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    MemberMapper memberMapper;

    @Override
    public String[] jwtLogin(String name,String id) {
        // jwt 발급하고 리턴하기
        String salt = UUID.randomUUID().toString();
        String access_token = JwtTokenProvider.createAccessToken(name,salt);
        String refresh_token = JwtTokenProvider.createRefreshToken(id,salt);

        Member m = new Member();
        m.setUserId(id);
        m.setSalt(salt);
        m.setAccessToken(access_token);
        m.setRefreshToken(refresh_token);
        memberMapper.saveToken(m);

        return new String[] {
                access_token,refresh_token
        };
    }
}
