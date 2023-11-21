package com.ssafy.enjoy.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomServletWrappingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filter get METHOD : " + request.getMethod());

        if ("POST".equals(request.getMethod())) {
            RequestFileWrapper requestFileWrapper = new RequestFileWrapper(request);
            requestFileWrapper.setAttribute("requestBody", requestFileWrapper.getRequestBody());

            try {
                filterChain.doFilter(requestFileWrapper, response);
            } catch (Exception e) {
                e.printStackTrace();
                filterChain.doFilter(requestFileWrapper, response);
            }
        } else {
            System.out.println("filter pass");
            filterChain.doFilter(request, response);
        }

    }
}
