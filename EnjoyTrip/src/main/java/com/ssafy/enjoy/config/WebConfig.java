package com.ssafy.enjoy.config;


import com.ssafy.enjoy.interceptor.UserInterceptor;
import com.ssafy.enjoy.session.SessionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserInterceptor())
				.addPathPatterns("/reHfPyFw/**") // 유저
				.excludePathPatterns("/reHfPyFw/tLL8srPp"); // 로그아웃
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("GET","POST")
				.allowedHeaders("*")
				.maxAge(6000);
	}
}
