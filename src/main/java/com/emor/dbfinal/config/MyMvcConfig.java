package com.emor.dbfinal.config;

import com.emor.dbfinal.conponent.LoginInterceptor;
import com.emor.dbfinal.conponent.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	public MyMvcConfig() {
		super();
	}

	@Bean
	LocaleResolver localeResolver(){
		return  new MyLocaleResolver();
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/login.html").setViewName("login");

		registry.addViewController("/register.html").setViewName("register");
		registry.addViewController("/register").setViewName("register");

		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/index.html").setViewName("index");

//		registry.addViewController("/admin_list").setViewName("admin_list");

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	}





}
