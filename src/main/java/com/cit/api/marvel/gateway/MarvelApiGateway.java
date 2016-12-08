package com.cit.api.marvel.gateway;

import java.util.Map;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

public interface MarvelApiGateway {

    public Response get(String resource, String queryString);
    
    public Response get(String resource, String queryString, Map<String, String> headers);

    public Response get(String resource, String queryString, Map<String, String> headers, String mediaType);

    public Invocation.Builder request(String resource, String queryString);
    
    public Invocation.Builder request(String resource, String queryString, Map<String, String> headers);

    public Invocation.Builder request(String resource, String queryString, Map<String, String> headers, String mediaType);

}
