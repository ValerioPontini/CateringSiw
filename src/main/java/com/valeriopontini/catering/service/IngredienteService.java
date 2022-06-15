package com.valeriopontini.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valeriopontini.catering.model.Ingrediente;
import com.valeriopontini.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ir;
	@Transactional
	public void save(Ingrediente ingrediente) {
		ir.save(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return ir.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente i : ir.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}

	public boolean alreadyExist(Ingrediente target) {
		return ir.existsByNomeAndOrigine(target.getNome(), target.getOrigine());
	}
}
