package com.valeriopontini.catering.model;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.ArrayList;

public class CustomAuthentication implements Authentication {

	Authentication auth;
	String ruolo;
	
	
	public CustomAuthentication(Authentication a, Role r) {
		this.auth = a;
		this.ruolo = r.getRuolo();
	}
	
	
	public Authentication getAuth() {
		return auth;
	}


	public void setAuth(Authentication auth) {
		this.auth = auth;
	}


	public String getRuolo() {
		return ruolo;
	}


	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}


	public void setRole(Role r) {
		this.ruolo = r.getRuolo();
	}
	
	public void setUser(Authentication a) {
		this.auth = a;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		 list.add(new SimpleGrantedAuthority(this.ruolo));
		return list;
	}

	

	

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.auth.getCredentials();
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return this.auth.getDetails();
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.auth.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return this.auth.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.auth.setAuthenticated(isAuthenticated);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.auth.getName();
	}

	
	

}
