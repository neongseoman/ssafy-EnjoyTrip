package com.ssafy.enjoy.interceptor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.enjoy.session.SessionService;
import com.ssafy.enjoy.session.model.SessionModel;
import com.ssafy.util.OpenCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

//

@Component
public class SessionInterceptor implements HandlerInterceptor {
    SessionService sessionService = SessionService.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!("POST".equals(request.getMethod())))  // if not post method
            return true;
        if (request.getAttribute("requestBody") == null) // if post method,req must have body.
            return false;
        String reqBody = (String) request.getAttribute("requestBody");
        if ("".equals(reqBody))   // if reqBody is empty
            return false;
        System.out.println("interceptor reqBody into " );
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(reqBody);
        JsonObject jsonObj = (JsonObject) obj;

        if (jsonObj.get("sessionId") == null)
            return false;
        System.out.println("session ID : " + jsonObj.get("sessionId"));
        String jsonObjSessionId = String.valueOf(jsonObj.get("sessionId"));
        String userSessionId = jsonObjSessionId.substring(1, jsonObjSessionId.length() - 1);
        // 너가 포스트로 보냈고 바디도 있고 바디가 비어있지 않고 바디에 세션아이디가 있다면 여기까지 올 수 있어.
        if (sessionService.isSessionValid(userSessionId)) {
            System.out.println("session is valid");
            SessionModel session = sessionService.getSession(userSessionId);
            LocalDateTime currentDateTime = LocalDateTime.now();
            // 세션 유효시간을 넘겼어.. 현재 시간 30분 이전보다 세션을 사용했다면
            if (!currentDateTime.minusMinutes(30).isBefore(session.getLatelyAccessTime()))
                return false; //돌아가.
            System.out.println("setTime");
            session.setLatelyAccessTime(LocalDateTime.now()); // 세션 유지 시간 갱신.
            String userAgent = request.getHeader("User-Agent");
            String reqHashedUserAgentSubString =
                    OpenCrypt.byteArrayToHex(OpenCrypt.getSHA1(userAgent, "ssafy")).substring(0,10);
            if (!reqHashedUserAgentSubString.equals(session.getHashedUserAgent())){ // agent가 다르다고 리젝트하기에는 너무 짜다~
                session.plusOneBlackListPoint();
            }
            if (session.getBlackListPoint() > 5) // 하지만 5점은 넘기면 안되지.
                return false;
//          여기까지 통과한 Req는 어느정도 믿어준다.
            request.setAttribute("userId", session.getUserId());
            request.setAttribute("userName", session.getUserName());
            request.setAttribute("sessionId",userSessionId);
            System.out.println("interceptor pass!");

            return true;
        } else { // if session is Valid return true, else return false
            return false;
        }
    }

}
