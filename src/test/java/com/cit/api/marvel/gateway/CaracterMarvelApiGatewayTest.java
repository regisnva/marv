//package com.cit.api.marvel.gateway;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.util.HashMap;
//
//import javax.ws.rs.core.Response;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.cit.api.marvel.gateway.impl.CharacterMarvelApiGateway;
//import com.cti.api.marvel.gateway.response.BaseResponse;
//import com.google.gson.GsonBuilder;
//
//public class CaracterMarvelApiGatewayTest {
//	
//	private MarvelApiGateway characterApiGateway;
//	private Response response;
//
//	@Before
//	public void setUp() throws Exception {
//		characterApiGateway = new CharacterMarvelApiGateway();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		characterApiGateway = null;
//		response = null;
//	}
//
//	@Test
//	public void shouldBeOkRequestTest() {
//		
//		try {
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("limit", "2");
//			
//			HashMap<String, String> headers = new HashMap<String, String>();
//			headers.put("Accept", "*/*");
//			
//			response = characterApiGateway.get(params, headers);
//			
//			assertEquals("Conflict request", 200, response.getStatus());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void shouldBeConflictRequestTest() {
//		
//		try {
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("limit", "1000");
//			
//			HashMap<String, String> headers = new HashMap<String, String>();
//			headers.put("Accept", "*/*");
//			
//			response = characterApiGateway.get(params, headers);
//			
//			assertEquals("Sucess request", 409, response.getStatus());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
//
//}
