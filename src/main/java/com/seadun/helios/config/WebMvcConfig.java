package com.seadun.helios.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.seadun.helios.interceptor.CorsInterceptor;
import com.seadun.helios.interceptor.TokenInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
		List<String> patterns = new ArrayList<>();
		patterns.add("/auth/**");
		patterns.add("/init/**");
		patterns.add("/iems/**");
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
		super.addInterceptors(registry);
	}
}
