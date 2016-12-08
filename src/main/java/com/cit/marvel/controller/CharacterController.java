package com.cit.marvel.controller;

import javax.servlet.http.HttpServletRequest;

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
import javax.ws.rs.core.GenericType;
import org.springframework.http.client.support.HttpAccessor;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    @Qualifier("MarvelApiGatewayImpl")
    private MarvelApiGateway marvelApiGateway;

    private MarvelResponse<MarvelCharacter> marvelResponse;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MarvelResponse<com.cit.api.marvel.gateway.model.MarvelCharacter>> getCharacters(HttpServletRequest req) {

        try {
            
            marvelResponse = marvelApiGateway.request(MarvelConstants.LIST_CHARACTERS_RESOURCE, req.getQueryString())
                    .get(new GenericType<MarvelResponse<MarvelCharacter>>(){});
            
            return new ResponseEntity(marvelResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
