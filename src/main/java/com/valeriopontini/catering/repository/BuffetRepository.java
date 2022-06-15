package com.valeriopontini.catering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.valeriopontini.catering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	
	public Optional<List<Buffet>> findByNome(String nome);
	
	public boolean existsByNome(String nome);

}
