package com.valeriopontini.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.valeriopontini.catering.model.Buffet;

import com.valeriopontini.catering.service.BuffetService;


@Component
public class BuffetValidator  implements Validator{
	@Autowired
	private BuffetService bs;
	
	@Override
	public boolean supports(Class<?> bClass){
		return Buffet.class.equals(bClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.bs.alreadyExist((Buffet) target)) {
			errors.reject("buffet.duplicato");
		}
		
	}

}
