package com.valeriopontini.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.valeriopontini.catering.model.Piatto;
import com.valeriopontini.catering.service.PiattoService;

@Component
public class PiattoValidator implements Validator{
	@Autowired
	private PiattoService ps;
	
	@Override
	public boolean supports(Class<?> pClass){
		return Piatto.class.equals(pClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.ps.alreadyExist((Piatto) target)) {
			errors.reject("piatto.duplicato");
		}
		
	}

}
