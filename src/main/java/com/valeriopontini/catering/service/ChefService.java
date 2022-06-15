package com.valeriopontini.catering.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valeriopontini.catering.model.Chef;
import com.valeriopontini.catering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	ChefRepository cr;
	@Transactional
	public void save(Chef chef) {
		cr.save(chef);
	}
	
	public Chef findById(Long id) {
		return cr.findById(id).get();
	}
	
	public List<Chef> findAll(){
		List<Chef> chefList = new ArrayList<Chef>();
		for(Chef c : cr.findAll()) {
			chefList.add(c);
		}
		return chefList;
	}
	
	public List<Chef> findByNome(String nome){
		String[] nomeECognome = nome.split(" ");
		if(nomeECognome.length > 1)
		return cr.findByNomeAndCognome(nomeECognome[0], nomeECognome[1]).orElse(null);
		else
	    return cr.findByNome(nome).orElse(cr.findByCognome(nome).orElse(null));
		
	}
	
	@Transactional
	public void delete(Long id) {
		cr.deleteById(id);
	}

	public boolean alreadyExist(Chef target) {
		return cr.existsByNomeAndCognome(target.getNome(), target.getCognome());
	}

	
}
