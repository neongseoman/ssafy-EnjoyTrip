package com.ssafy.enjoy.session;

import com.ssafy.enjoy.session.model.LoginModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("/session/map")
public class LoginSession { // 세션은 빈번하게 사용되니까 static으로 만들까?
    Map<String, LoginModel> loginSession = new HashMap<>();
    static final String NODE_URL = "http://localhost:3000";
    static Map<String,String> user_session = new HashMap<>();

    @PostMapping("login")
    public boolean login(@RequestBody Map<String,String> reqBody) throws IOException {
//        System.out.println(reqBody);
        URL obj = new URL(NODE_URL+"/session"); // 이것도 보안 처리를 해야할까? 암호화....
        HttpURLConnection con = (HttpURLConnection)  obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
//        String sendJsonData = "{id:"+ reqBody.get("id") + "}";
        String sendJsonData = "{\"id\":\"" + reqBody.get("id") + "\"}";
        System.out.println(sendJsonData);

        try (OutputStream os = con.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            // Write the JSON data to the output stream
            osw.write(sendJsonData);
            osw.flush();
        }

        int responseCode = con.getResponseCode();
        System.out.println("Node Response Code : " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String hash_id;
            StringBuilder response = new StringBuilder();
            while ((hash_id = br.readLine()) != null)
                response.append(hash_id);
            user_session.put(hash_id,hash_id);

            br.close();
            System.out.println(response);
            return true;
        }else{
            System.out.println("Req Fail");
            return false;
        }
//        return true;
    }

    public boolean isLogin(String sessionId){
        return false;
    }

    public LoginModel getSession(String sessionId){
        return loginSession.get(sessionId);
    }

    public void invalidate(String sessionId){}

}
