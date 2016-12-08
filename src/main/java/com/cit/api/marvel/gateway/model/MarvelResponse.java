package com.cit.api.marvel.gateway.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class MarvelResponse<T> {

	private String code;
	private String message;
	private String status;
	private String etag;
	private String copyright;
	private String attributionText;
	private String attributionHTML;
	
        @JsonIgnore
        private Data<T> data;

	@SuppressWarnings("rawtypes")
	public static <T>MarvelResponse create(String json) {
		return new GsonBuilder().create().fromJson(json, new TypeToken<MarvelResponse<T>>(){}.getType());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getAttributionText() {
		return attributionText;
	}

	public void setAttributionText(String attributionText) {
		this.attributionText = attributionText;
	}

	public String getAttributionHTML() {
		return attributionHTML;
	}

	public void setAttributionHTML(String attributionHTML) {
		this.attributionHTML = attributionHTML;
	}

	public Data<T> getData() {
		return data;
	}

	public void setData(Data<T> data) {
		this.data = data;
	}
}
