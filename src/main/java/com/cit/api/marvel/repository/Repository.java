package com.cit.api.marvel.repository;

import java.util.List;

public interface Repository<T> {
	
	public void save(T object);
	
	public void delete(T object);
	public void deleteAll();
	
	public List<T> find();	
	public T findById(int id);
}
