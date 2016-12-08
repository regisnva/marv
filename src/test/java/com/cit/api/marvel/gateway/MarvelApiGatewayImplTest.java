package com.cit.api.marvel.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cit.api.marvel.gateway.impl.MarvelApiGatewayImpl;
import com.cit.api.marvel.gateway.model.MarvelCharacter;
import com.cit.api.marvel.gateway.model.MarvelResponse;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
    public void shouldBeGetCharacetersByJacksonTest() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, "limit=2");

            String json = response.readEntity(String.class);
            
            MarvelResponse<MarvelCharacter> marvelResponse = mapper.readValue(json, MarvelResponse.class);

            assertNotNull(marvelResponse);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
