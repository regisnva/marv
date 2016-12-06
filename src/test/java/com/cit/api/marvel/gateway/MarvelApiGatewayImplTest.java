package com.cit.api.marvel.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cit.api.marvel.gateway.impl.MarvelApiGatewayImpl;


public class MarvelApiGatewayImplTest {
	
	private MarvelApiGateway marvelApiGateway;
	private Response response;


	@Before
	public void setUp() throws Exception {
		marvelApiGateway = new MarvelApiGatewayImpl();
	}

	@After
	public void tearDown() throws Exception {
		marvelApiGateway = null;
	}
	
	@Test
	public void shouldBeOkRequestTest() {
		
		try {
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("Accept", "*/*");
			
			response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2", headers, MediaType.APPLICATION_JSON_TYPE);
			
			assertEquals("Conflict request", 200, response.getStatus());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}


}
