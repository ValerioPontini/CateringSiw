package com.valeriopontini.catering.service;

import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valeriopontini.catering.model.Piatto;
import com.valeriopontini.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	PiattoRepository pr;
	@Transactional
	public void save(Piatto piatto) {
		pr.save(piatto);
	}
	
	@Transactional
	public void delete(Long id) {
		pr.deleteById(id);
	}
	
	public Piatto findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : pr.findAll())
			piatti.add(p);
		return piatti;
	}
	
	public boolean alreadyExist(Piatto piatto) {
		return pr.existsBynome(piatto.getNome());
	}

	public List<Piatto> findByNome(String nome) {
		return pr.findByNome(nome).orElse(null);
	}
	
	
}
