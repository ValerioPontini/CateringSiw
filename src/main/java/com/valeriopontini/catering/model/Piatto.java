package com.valeriopontini.catering.model;

import java.util.Comparator;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String nome;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingrediente> ingredienti;
	@ManyToOne
	private Buffet buffet;
	
	public Buffet getBuffet() {
		return buffet;
	}
	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	
	}


