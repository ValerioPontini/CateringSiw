package com.valeriopontini.catering.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.valeriopontini.catering.controller.validator.IngredienteValidator;
import com.valeriopontini.catering.model.Ingrediente;
import com.valeriopontini.catering.model.comparator.IngredienteComparator;
import com.valeriopontini.catering.service.IngredienteService;


@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService is;
	@Autowired
	IngredienteValidator validator;
	
	
	/*
	 * convenzione get per scrittura post per lettura
	 * path associato a classi del dominio
	 */
	
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente")Ingrediente ingrediente, BindingResult br, Model model) {
		validator.validate(ingrediente, br);
		if(!br.hasErrors()) {
			this.is.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "ingrediente.html";
		}
		return "ingredienteForm.html";
	}
	//richiede tutte le persone perchè non specificato id
	
	@GetMapping("/ingrediente")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = is.findAll();
		ingredienti.sort(new IngredienteComparator());
		model.addAttribute("ingredienti", ingredienti);
		return "ingrediente.html";
		
	}
	
	@GetMapping("/ingrediente/{id}")
		public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = is.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}
	
	@GetMapping("/ingredienteForm")
	public String getIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}
	
	
	
	
	
	
	
	
}
