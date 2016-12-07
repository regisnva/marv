package com.citi.api.marvel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cit.api.marvel.gateway.MarvelApiGateway;
import com.cit.api.marvel.gateway.conf.MarvelConf;

@EnableWebMvc
@ComponentScan(basePackageClasses = { MarvelConf.class, MarvelApiGateway.class })
public class AppWebConf {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver (){
		
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		
		internalResourceViewResolver.setPrefix("/WEB-INF");
		internalResourceViewResolver.setSuffix("*.jsp");
		
		return internalResourceViewResolver;
		
	}

}
