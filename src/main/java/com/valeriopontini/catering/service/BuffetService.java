package com.valeriopontini.catering.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valeriopontini.catering.model.Buffet;
import com.valeriopontini.catering.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	BuffetRepository br;
	
	@Transactional
	public void save(Buffet buffet) {
		br.save(buffet);
	}

	public Buffet findById(Long id) {
		return br.findById(id).get();
	}
	
	public List<Buffet> findAll(){
		List<Buffet> buffetList = new ArrayList<Buffet>();
		for(Buffet b : br.findAll())
			buffetList.add(b);
		return buffetList;
	}
	
	public List<Buffet> findByNome(String nome){
		return br.findByNome(nome).orElse(null);
	}
	
	@Transactional
	public void delete(Long id) {
		 br.deleteById(id);
	}

	public boolean alreadyExist(Buffet target) {
		return br.existsByNome(target.getNome());
	}
}
