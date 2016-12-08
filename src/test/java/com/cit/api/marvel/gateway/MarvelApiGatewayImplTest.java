package com.cit.api.marvel.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cit.api.marvel.conf.AppWebConf;
import com.cit.api.marvel.gateway.model.MarvelCharacter;
import com.cit.api.marvel.gateway.model.MarvelResponse;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppWebConf.class })
@WebAppConfiguration
public class MarvelApiGatewayImplTest {

    @Autowired
    @Qualifier("MarvelApiGatewayImpl")
	private MarvelApiGateway marvelApiGateway;
    
    private Response response;
    private MarvelResponse<MarvelCharacter> marvelCharacterResponse; 

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    	response = null;
    	marvelCharacterResponse = null;
    }

    @Test
    @Ignore
    public void shouldBeOkRequestTest() {

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "*/*");

            response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2", headers,
                    MediaType.APPLICATION_JSON);

            assertEquals("Conflict request", 200, response.getStatus());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Ignore
    public void shouldBeGetCharacetersTest() {

        try {
            response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2");

            MarvelResponse<MarvelCharacter> characterResponse = new GsonBuilder().create()
                    .fromJson(response.readEntity(String.class), new TypeToken<MarvelResponse<MarvelCharacter>>() {
                    }.getType());

            assertNotNull(characterResponse);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void shouldBeGetCharactersTest(){
    	
    	try {
    		marvelCharacterResponse = marvelApiGateway.request(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2")
                    .get(new GenericType<MarvelResponse<MarvelCharacter>>(){});
    		
    		assertNotNull(marvelCharacterResponse);

    	} catch (Exception e) {
            fail(e.getMessage());
    	}
    }
}
	