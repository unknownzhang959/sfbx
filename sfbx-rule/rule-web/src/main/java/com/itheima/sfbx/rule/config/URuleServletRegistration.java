package com.itheima.sfbx.rule.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.itheima.sfbx.rule.console.servlet.URuleServlet;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
public class URuleServletRegistration {
	
	@Bean
	public ServletRegistrationBean registerURuleServlet(){
		return new ServletRegistrationBean(new URuleServlet(),"/urule/*");
	}
	
	@Bean
	public ServletRegistrationBean registerIndexServlet(){
		return new ServletRegistrationBean(new IndexServlet(),"/");
	}
}

