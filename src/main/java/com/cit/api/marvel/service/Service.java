package com.cit.api.marvel.service;

import java.util.List;

public interface Service<T> {
	
	public void save(T entity);
	public List<T> findAll();

}
