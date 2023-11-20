package com.ssafy.enjoy.interceptor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.enjoy.session.SessionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//

@Component
public class UserInterceptor implements HandlerInterceptor {

    SessionService sessionService = SessionService.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqBody = (String) request.getAttribute("requestBody");

        System.out.println("interceptor reqBody : " + reqBody);
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(reqBody);
        JsonObject jsonObj = (JsonObject) obj;

        String jsonObjSessionId = String.valueOf(jsonObj.get("sessionId"));
        String userSessionId = jsonObjSessionId.substring(1,jsonObjSessionId.length()-1);

        if (sessionService.isSessionValid(userSessionId)) {
//            Enumeration<String > e  = request.getHeaderNames();
//            while(e.hasMoreElements()){
//                String n = e.nextElement();
//                System.out.println(n + " : "+ request.getHeader(n));
//            }
//            System.out.println("interceptor pass!");
            return true;
        }
        else {
            System.out.println("error");
            response.setStatus(999);
            return false;
        }

//        System.out.println("interceptor " + request.getMethod());
//        return true;

    }

}
