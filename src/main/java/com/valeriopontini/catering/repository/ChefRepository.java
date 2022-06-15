package com.valeriopontini.catering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.valeriopontini.catering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {
	
	public Optional<List<Chef>> findByNomeAndCognome(String nome, String cognome);

	public Optional<List<Chef>> findByNome(String nome);

	public Optional<List<Chef>> findByCognome(String cognome);
	
	public boolean existsByNomeAndCognome(String nome, String cognome);

	
}
