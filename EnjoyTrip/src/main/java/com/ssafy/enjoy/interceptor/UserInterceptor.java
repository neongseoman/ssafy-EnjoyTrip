package com.ssafy.enjoy.interceptor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.enjoy.session.SessionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//

@Component
public class UserInterceptor implements HandlerInterceptor {

    SessionService sessionService = SessionService.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("POST".equals(request.getMethod())) {
            if (request.getAttribute("requestBody") == null) // if post method,req must have body.
                return false;
            String reqBody = (String) request.getAttribute("requestBody");
            if (!("".equals(reqBody)) ) { // if reqBody is not empty
                System.out.println("interceptor reqBody : " + reqBody);
                JsonParser jsonParser = new JsonParser();
                Object obj = jsonParser.parse(reqBody);
                JsonObject jsonObj = (JsonObject) obj;

                if (jsonObj.get("sessionId") != null) {
                    String jsonObjSessionId = String.valueOf(jsonObj.get("sessionId"));
                    String userSessionId = jsonObjSessionId.substring(1, jsonObjSessionId.length() - 1);

                    if (sessionService.isSessionValid(userSessionId)) {
//                    Enumeration<String> e = request.getHeaderNames();
//                    while (e.hasMoreElements()) {
//                        String n = e.nextElement();
//                        System.out.println(n + " : " + request.getHeader(n));
                        System.out.println("interceptor pass!");
                        return true;
                    } else { // if session is Valid return true, else return false
                        return false;
                    }
                } else{
                    return false;
                }
            } else { // if requestBody is null return false. if send post, must bring body.
                return false;
            }
        } else { // if not post method, pass
            return true;

        }
    }

}
