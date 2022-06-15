package com.valeriopontini.catering.model.comparator;

import java.util.Comparator;

import com.valeriopontini.catering.model.Piatto;

public class PiattoComparator implements Comparator<Piatto>{
	
	@Override
	public int compare(Piatto p1, Piatto p2) {
		
			return p1.getNome().compareTo(p2.getNome());
	
	
}
	}