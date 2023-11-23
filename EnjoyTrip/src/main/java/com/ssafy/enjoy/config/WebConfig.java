package com.ssafy.enjoy.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.enjoy.interceptor.SessionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
				.addPathPatterns("/reHfPyFw/**", "/Crh17XqZ/**", "/YZ97gY92/**", "/MytfEaEE/**") // 유저
				.excludePathPatterns("/reHfPyFw/MAqGI3Cv", "/reHfPyFw/EpMrOzqa","/reHfPyFw/ZbsgU6oY"); // 로그인, 아이디 체크, 회원가입 제외
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST")
				.allowedHeaders("*")
				.maxAge(6000);
	}
}
