package com.valeriopontini.catering.model.comparator;

import java.util.Comparator;

import com.valeriopontini.catering.model.Ingrediente;

public class IngredienteComparator implements Comparator<Ingrediente>{
	
	@Override
	public int compare(Ingrediente i1, Ingrediente i2) {
		return i1.getNome().compareTo(i2.getNome());		
}
	}