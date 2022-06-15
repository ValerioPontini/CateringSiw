package com.valeriopontini.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.valeriopontini.catering.model.Chef;
import com.valeriopontini.catering.service.ChefService;


@Component
public class ChefValidator implements Validator{
	@Autowired
	private ChefService cs;
	
	@Override
	public boolean supports(Class<?> cClass){
		return Chef.class.equals(cClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.cs.alreadyExist((Chef) target)) {
			errors.reject("chef.duplicato");
		}
		
	}
}
