//package com.cit.api.marvel.gateway.impl;
//
//import java.util.HashMap;
//
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//
//public class CharacterMarvelApiGateway extends MarvelApiGatewayImpl {
//	
//	public CharacterMarvelApiGateway() {
//		super();
//	}
//	
//	@Override
//	public String getResource() {
//		return "characters";
//	}
//
//	@Override
//	public WebTarget path() {
//		return this.webTarget.path("characters");
//	}
//
//	@Override
//	public Response get(HashMap<String, Object> params, HashMap<String, String> headers) {
//		return super.get(MediaType.APPLICATION_JSON_TYPE, params, headers);
//	}
//
//}
