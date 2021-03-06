package com.cit.api.marvel.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cit.api.marvel.controller.CharacterController;
import com.cit.api.marvel.gateway.MarvelApiGateway;
import com.cit.api.marvel.gateway.conf.MarvelConf;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = {CharacterController.class, MarvelConf.class, MarvelApiGateway.class })
public class AppWebConf extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    public MappingJackson2HttpMessageConverter converter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }
    
    @Bean
    public CacheManager getEhCacheManager(){
    	return new EhCacheCacheManager(getEhCacheFactory().getObject());
    }
    
    @Bean
    public EhCacheManagerFactoryBean getEhCacheFactory(){
    	EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
    	
    	ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
    	ehCacheManagerFactoryBean.setShared(true);
    	
    	return ehCacheManagerFactoryBean;
    }
}
