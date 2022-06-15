package com.valeriopontini.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.valeriopontini.catering.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	public boolean existsByNomeAndOrigine(String nome, String origine);
}
