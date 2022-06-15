package com.valeriopontini.catering.model;

import java.util.Comparator;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"Nome", "Cognome"}))
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String nazionalita;
	@OneToMany( mappedBy = "chef", cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER )
	private List<Buffet> buffet;
	
	public List<Buffet> getBuffet() {
		return buffet;
	}
	public void setBuffet(List<Buffet> buffet) {
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	
	

}
