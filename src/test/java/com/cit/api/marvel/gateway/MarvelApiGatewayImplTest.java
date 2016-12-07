package com.cit.api.marvel.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cit.api.marvel.gateway.impl.MarvelApiGatewayImpl;
import com.cti.api.marvel.gateway.model.Character;
import com.cti.api.marvel.gateway.model.MarvelResponse;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import junit.framework.Assert;


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
	@Ignore
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
	
	@Test
	public void shouldBeGetCharacetersTest(){
		
		try {
			
			response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2");
			
			MarvelResponse<Character> characterResponse = 
					new GsonBuilder().create().fromJson(response.readEntity(String.class), 
					new TypeToken<MarvelResponse<Character>>(){}.getType());

			Assert.assertNotNull(characterResponse);
			
		} catch (Exception e){
			
		}
	}


}
