package com.cit.api.marvel.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cit.api.marvel.gateway.model.MarvelCharacter;

@Repository
public class CharacterRepository implements com.cit.api.marvel.repository.Repository<MarvelCharacter> {

	private static List<MarvelCharacter> marvelCharacters = new ArrayList<>();

	@Override
	public void save(MarvelCharacter object) {
		marvelCharacters.add(object);
	}

	@Override
	public void delete(MarvelCharacter object) {
		marvelCharacters.remove(object);
	}

	@Override
	public void deleteAll() {
		marvelCharacters.clear();
	}

	@Override
	public List<MarvelCharacter> find() {
		return marvelCharacters;
	}

	@Override
	public MarvelCharacter findById(int id) {
		Optional<MarvelCharacter> result = marvelCharacters.stream()
				.filter(m -> m.getId() == id)
				.findFirst();

		return result != null ? result.get() : new MarvelCharacter();
	}
}
