package com.ssafy.enjoy.session;

import com.ssafy.enjoy.member.model.vo.MemberVo;
import com.ssafy.enjoy.session.model.SessionModel;
import com.ssafy.enjoy.session.model.SessionReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class SessionService { // 세션은 빈번하게 사용되니까
    private static SessionService instance;
    private SessionService(){}
    public static SessionService getInstance(){
        if (instance == null){
            instance = new SessionService();
        }
        return instance;
    }


    static Map<String, SessionModel> session = new HashMap<>();

    @Value("${NODE-URL}")
    private String NODE_URL;

    public String sessionReq(SessionReqDto reqBody, MemberVo userInfo, String hashedUserAgent) throws IOException {
//        System.out.println(reqBody.toString());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application","json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        String sessionId = restTemplate.postForObject(NODE_URL+"/session", reqBody, String.class);
        SessionModel sessionModel
                = new SessionModel(reqBody.getUserId(),userInfo.getUserName(), sessionId,hashedUserAgent,0, LocalDateTime.now());
        // session ID가 없을 때 전략 필요. node에 req를 다시 보낼 것인가?
        session.put(sessionId,sessionModel);
        System.out.println("this is session " + session.get(sessionId));
        return sessionId;
    }

    public boolean isSessionValid(String userId){
        return session.get(userId) != null;
    }

    public SessionModel getSession(String userId){
        try{
            if (isSessionValid(userId))
                return session.get(userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    return null;
    }

    public void invalidate(String userId){
        if (isSessionValid(userId)){
            session.remove(userId);
        }
    }

    public Map<String, SessionModel> getSession() {
        return session;
    }

    public String testSession() {
        return "session Hello";
    }


}
