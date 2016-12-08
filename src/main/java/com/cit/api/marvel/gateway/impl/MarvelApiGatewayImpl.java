package com.cit.api.marvel.gateway.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cit.api.marvel.gateway.MarvelApiGateway;
import com.cit.api.marvel.gateway.MarvelConstants;
import com.cit.api.marvel.gateway.conf.MarvelConf;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

@Component("MarvelApiGatewayImpl")
public class MarvelApiGatewayImpl implements MarvelApiGateway {

	private Client client;
	private WebTarget webTarget;

	@Autowired
	private MarvelConf marvelConf;

	public MarvelApiGatewayImpl() {		
		initClient();
	}

	private void initClient() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	}

	@Override
	public Response get(String resource, String queryString) {
		return get(resource, queryString, null, MediaType.APPLICATION_JSON);
	}
	
	@Override
	public Response get(String resource, String queryString, Map<String, String> headers) {
		return get(resource, queryString, headers, MediaType.APPLICATION_JSON);
	}

	@Override
	public Response get(String resource, String queryString, Map<String, String> headers, String mediaType) {
		return request(resource, queryString, headers, mediaType).get();
	}

	@Override
	public Invocation.Builder request(String resource, String queryString) {
		return request(resource, queryString, null, MediaType.APPLICATION_JSON);
	}

	@Override
	public Builder request(String resource, String queryString, Map<String, String> headers) {
		return request(resource, queryString, headers, MediaType.APPLICATION_JSON);
	}

	@Override
	public Invocation.Builder request(String resource, String queryString, Map<String, String> headers,
			String mediaType) {

		Invocation.Builder invocationBuilder = getWebTarget(resource, getQueryParams(queryString)).request(mediaType);

		if (headers != null) {
			headers.forEach((k, v) -> invocationBuilder.header(k, v));
		}

		return invocationBuilder;
	}

	private WebTarget getWebTarget(String resource, HashMap<String, Object> params) {
		webTarget = this.client.target(getUri(resource));

		if (params != null) {
			params.forEach((k, v) -> webTarget = webTarget.queryParam(k, v));
		}

		return webTarget;
	}

	private UriBuilder getUri(String resource) {
		String ts = UUID.randomUUID().toString();

		UriBuilder uri = UriBuilder.fromPath(marvelConf.getDomain() + resource);

		uri.queryParam(MarvelConstants.QUERY_TS, ts)
				.queryParam(MarvelConstants.QUERY_APP_KEY, marvelConf.getPublicKey()).queryParam(
						MarvelConstants.QUERY_HASH, hash(ts, marvelConf.getPublicKey(), marvelConf.getPrivateKey()));

		return uri;
	}

	private String hash(String ts, String publicKey, String privateKey) {
		HashFunction hash = Hashing.md5();
		HashCode code = hash.newHasher().putString(ts, Charsets.UTF_8).putString(privateKey, Charsets.UTF_8)
				.putString(publicKey, Charsets.UTF_8).hash();

		return code.toString();
	}

	private HashMap<String, Object> getQueryParams(final String queryString) {
		HashMap<String, Object> mapQueryParams = new HashMap<String, Object>();

		if (queryString == null) {
			return mapQueryParams;
		}

		String[] splitQueryString = queryString.split("&");

		if (splitQueryString == null) {
			return mapQueryParams;
		}

		for (String queryParameter : splitQueryString) {
			String[] queryParameterArgs = queryParameter.split("=");

			if (queryParameterArgs != null && queryParameterArgs.length == 2) {
				mapQueryParams.put(queryParameterArgs[0], queryParameterArgs[1]);
			}

		}

		return mapQueryParams;
	}

}
