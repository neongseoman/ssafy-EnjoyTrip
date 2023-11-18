package com.ssafy.enjoy.session;

import com.ssafy.enjoy.session.model.SessionModel;
import com.ssafy.enjoy.session.model.SessionResModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class SessionService { // 세션은 빈번하게 사용되니까 static으로 만들까?

    Map<String, SessionModel> session = new HashMap<>();

    @Value("${NODE-URL}")
    private String NODE_URL;

//    @PostMapping("login")
    public boolean login(@RequestBody Map<String, String> reqBody) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application","json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        SessionResModel res = restTemplate.postForObject(NODE_URL+"/session", reqBody, SessionResModel.class);
        System.out.println(res);
        return true;
    }

    public boolean isSessionValid(String userId){
        if (session.get(userId) != null){
            return true;
        } else{
            return false;
        }
    }

    public SessionModel getSession(String userId) throws Exception {
        if (isSessionValid(userId))
            return session.get(userId);
        throw new Exception(); // 세션이 없다면 Exception 처리 해야함.
    }

    public void invalidate(String userId){
        if (isSessionValid(userId)){
            session.remove(userId);
        }
    }

    public void accessTimeModify(String userId, Date accessTime){
        SessionModel sessionModel = session.get(userId);
        sessionModel.setLastAccessTime(accessTime);
        session.put(userId,sessionModel);
    }

}
