package com.ssafy.enjoy.interceptor;

import com.ssafy.enjoy.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    final SessionService sessionService;

    public UserInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        sessionService.getSession("hello");
//        System.out.println(request.getHeaderNames());
        System.out.println("interceptor hello");
        System.out.println(sessionService);
        sessionService.testSession();
        return true;
    }

}
