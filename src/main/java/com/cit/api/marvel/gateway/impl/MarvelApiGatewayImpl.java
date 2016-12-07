package com.cit.api.marvel.gateway.impl;

import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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
	// @Autowired
	private MarvelConf marvelConf;

	// public MarvelApiGatewayImpl() {
	//
	// initClient();
	// initWebTarget();
	// }

	public MarvelApiGatewayImpl() {
		initClient();
		marvelConf = new MarvelConf();
	}

	private void initClient() {
		client = ClientBuilder.newBuilder().build();
	}

	@Override
	public Response get(String resource, String queryString) {
		return this.get(resource, queryString, null, MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public Response get(String resource, String queryString, HashMap<String, String> headers, MediaType type) {
		return request(resource, queryString, headers, type).get();
	}

	private Invocation.Builder request(String resource, String queryString, HashMap<String, String> headers,
			MediaType type) {

		Invocation.Builder invocationBuilder = getWebTarget(resource, getQueryParams(queryString)).request(type);

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

	// private void initWebTarget() {
	//
	// if (this.webTarget == null) {
	// this.webTarget = client.target(marvelConf.getDomain());
	// }
	// }

	// private void params(HashMap<String, Object> params) {
	//
	// this.webTarget = this.webTarget.queryParam("ts", 1);
	// this.webTarget = this.webTarget.queryParam("apikey",
	// "acc13928d0d03715ac7e690848de97ee");
	// this.webTarget = this.webTarget.queryParam("hash",
	// "73b9de59327da9b9267fa95bf29b117a");
	//
	// if (params != null) {
	// params.forEach((k, v) -> this.webTarget = this.webTarget.queryParam(k,
	// v));
	// }
	// }

	// private void params(HashMap<String, Object> params) {
	//
	// this.webTarget = this.webTarget.queryParam("ts", 1);
	// this.webTarget = this.webTarget.queryParam("apikey",
	// "acc13928d0d03715ac7e690848de97ee");
	// this.webTarget = this.webTarget.queryParam("hash",
	// "73b9de59327da9b9267fa95bf29b117a");
	//
	// if (params != null) {
	// params.forEach((k, v) -> this.webTarget = this.webTarget.queryParam(k,
	// v));
	// }
	// }

	// private Invocation.Builder request(MediaType type, HashMap<String,
	// String> headers) {
	//
	// Invocation.Builder invocationBuilder = this.webTarget.request(type);
	//
	// if (headers != null) {
	// headers.forEach((k, v) -> invocationBuilder.header(k, v));
	// }
	//
	// return invocationBuilder;
	// }

	// protected Response get(MediaType type, HashMap<String, Object> params,
	// HashMap<String, String> headers) {
	// this.webTarget = this.path();
	// this.params(params);
	// return request(type, headers).get();
	// }
}
