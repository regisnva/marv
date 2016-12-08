package com.cit.marvel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cit.api.marvel.gateway.MarvelApiGateway;
import com.cit.api.marvel.gateway.MarvelConstants;
import com.cit.api.marvel.gateway.model.MarvelCharacter;
import com.cit.api.marvel.gateway.model.MarvelResponse;

@Controller
@RequestMapping("/characters")
public class CharacterController extends BaseController {

    @Autowired
    @Qualifier("MarvelApiGatewayImpl")
    private MarvelApiGateway marvelApiGateway;

    private MarvelResponse<MarvelCharacter> marvelResponse;
    
    private static final Logger logger = LogManager.getLogger(CharacterController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MarvelResponse<MarvelCharacter>> getCharacters(HttpServletRequest req) {
    	
    	logger.info("call getCharacters");
    	
    	marvelResponse = new MarvelResponse<>();

        try {
        	
        	Response response = marvelApiGateway.get(MarvelConstants.LIST_CHARACTERS_RESOURCE, req.getQueryString(), getHeaders(req));
        	
        	if(response.getStatus() == HttpStatus.NOT_MODIFIED.value()){
                return new ResponseEntity<>(marvelResponse, HttpStatus.NOT_MODIFIED);
        	}
        	
        	marvelResponse = response.readEntity(new GenericType<MarvelResponse<MarvelCharacter>>(){});
            return new ResponseEntity<>(marvelResponse, HttpStatus.valueOf(response.getStatus()));
        } catch (Exception ex) {
        	marvelResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(marvelResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
