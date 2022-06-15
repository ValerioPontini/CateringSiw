package com.valeriopontini.catering.model.comparator;

import java.util.Comparator;

import com.valeriopontini.catering.model.Buffet;

public class BuffetComparator implements Comparator<Buffet>{
	
	@Override
	public int compare(Buffet b1, Buffet b2) {
		
			return b1.getNome().compareTo(b2.getNome());
		
	
}
	}