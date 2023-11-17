package com.ssafy.enjoy.interceptor;

import com.ssafy.enjoy.session.SessionService;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;


public class userInterceptor implements HandlerInterceptor {

    SessionService session = SessionService.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // request의 쿠키 따서 userId 만듬.
//        String userID = null;
//                if (session.isSessionValid(userID)){
//                    session.accessTimeModify(userID,LocalDate.now());
//                    return true;
//        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
