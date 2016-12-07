package com.citi.marvel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cit.api.marvel.gateway.MarvelApiGateway;

@Controller
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	@Qualifier("MarvelApiGatewayImpl")
	private MarvelApiGateway marvelApiGateway;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Character> getCharacters(HttpServletRequest req){
		
		try {
			return new ResponseEntity<Character>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
