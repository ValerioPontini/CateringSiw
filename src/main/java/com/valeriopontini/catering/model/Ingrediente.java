package com.valeriopontini.catering.model;

import java.util.Comparator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "origine"}))
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String origine;
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	
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
	
	public String getOrigine() {
		return origine;
	}
	
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	}


