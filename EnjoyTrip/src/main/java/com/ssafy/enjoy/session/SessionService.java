package com.ssafy.enjoy.session;

import com.ssafy.enjoy.session.model.SessionModel;
import com.ssafy.enjoy.session.model.SessionReqModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class SessionService { // 세션은 빈번하게 사용되니까 static으로 만들까?

    Map<java.lang.String, SessionModel> session = new HashMap<>();

    @Value("${NODE-URL}")
    private java.lang.String NODE_URL;

    public String sessionReq(SessionReqModel reqBody) throws IOException {
//        System.out.println(reqBody.toString());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application","json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        String sessionId = restTemplate.postForObject(NODE_URL+"/session", reqBody, String.class);
        SessionModel sessionModel = new SessionModel(reqBody.getUserId(), sessionId);
        // session ID가 없을 때 전략 필요. node에 req를 다시 보낼 것인가?
        session.put(sessionId,sessionModel);
        System.out.println(session.get(sessionId));
        return sessionId;
    }

    public boolean isSessionValid(java.lang.String userId){
        if (session.get(userId) != null){
            return true;
        } else{
            return false;
        }
    }

    public SessionModel getSession(java.lang.String userId){
        try{
            if (isSessionValid(userId))
                return session.get(userId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    return null;
    }

    public void invalidate(java.lang.String userId){
        if (isSessionValid(userId)){
            session.remove(userId);
        }
    }

    public String testSession() {
        return "session Hello";
    }
//
//    public void accessTimeModify(java.lang.String userId, Date accessTime){
//        SessionModel sessionModel = session.get(userId);
//        sessionModel.setLastAccessTime(accessTime);
//        session.put(userId,sessionModel);
//    }

}
