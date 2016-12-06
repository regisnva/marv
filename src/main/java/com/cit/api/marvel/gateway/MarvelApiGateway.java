package com.cit.api.marvel.gateway;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface MarvelApiGateway {
	
//	public WebTarget path();
	
	public Response get(String resource, String queryString, HashMap<String, String> headers, MediaType type);
	
	//public HashMap<String, Object> getQueryParams(String queryString);
	
//	public Response get(HashMap<String, Object> params, HashMap<String, String> headers);

	
}
