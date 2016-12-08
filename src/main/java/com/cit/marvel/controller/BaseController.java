package com.cit.marvel.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.cit.api.marvel.gateway.MarvelConstants;


public abstract class BaseController {
	
	protected Map<String, String> getHeaders(HttpServletRequest req){
		
		HashMap<String, String> headers = new HashMap<>();
		
		for (Enumeration<String> enu = req.getHeaderNames(); enu.hasMoreElements();) {
			String headerName = enu.nextElement();
			
			if (!StringUtils.hasText(headerName)){
				return headers;
			}
			
			String headerValue = req.getHeader(headerName);
			
			if(StringUtils.hasText(headerValue)){
				headers.put(headerName, headerValue);
			}
		}
		
		return headers;
	}
}
