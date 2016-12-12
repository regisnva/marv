package com.cit.api.marvel.gateway.conf;

import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:/marvelapi.properties")
public class MarvelConf {
	
	private final String domain = "https://gateway.marvel.com/v1/public/";
	private final String publicKey = "acc13928d0d03715ac7e690848de97ee";
	private final String privateKey = "ddd0568211c2aceb2bd81cad833f0cf17e84d687";
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	
	public String getDomain() {
		return domain;
	}
}
