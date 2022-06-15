package com.valeriopontini.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.security.oauth2.core.user.OAuth2User;

@Entity
public class Role {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Id
	private Long id;
	
	@NotBlank
	@Column
	private String ruolo;
	
	@Column
	private String authUser;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getUser() {
		return authUser;
	}

	public void setUser(OAuth2User user) {
		this.authUser = user.getName();
	}
}
