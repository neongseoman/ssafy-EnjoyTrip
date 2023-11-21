package com.ssafy.enjoy.config;


import com.ssafy.enjoy.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
				.addPathPatterns("/reHfPyFw/**") // 유저
				.excludePathPatterns("/reHfPyFw/MAqGI3Cv"); // 로그아웃
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
