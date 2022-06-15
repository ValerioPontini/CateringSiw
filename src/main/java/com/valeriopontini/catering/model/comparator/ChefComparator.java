package com.valeriopontini.catering.model.comparator;

import java.util.Comparator;

import com.valeriopontini.catering.model.Chef;

public class ChefComparator implements Comparator<Chef>{
	
	@Override
	public int compare(Chef c1, Chef c2) {
		if(c1.getNome().equals(c2.getNome()))
			return c1.getCognome().compareTo(c2.getCognome());
		else
			return c1.getNome().compareTo(c2.getNome());
	
	
}
	}
