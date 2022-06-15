package com.valeriopontini.catering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.valeriopontini.catering.model.Ingrediente;
import com.valeriopontini.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	public boolean existsBynome(String nome);

	public Optional<List<Piatto>> findByNome(String nome);
	
	
	
}
