package com.valeriopontini.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.valeriopontini.catering.model.Ingrediente;
import com.valeriopontini.catering.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator{
	@Autowired
	private IngredienteService is;
	
	@Override
	public boolean supports(Class<?> iClass){
		return Ingrediente.class.equals(iClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.is.alreadyExist((Ingrediente) target)) {
			errors.reject("ingrediente.duplicato");
		}
		
	}
}
