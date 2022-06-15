package com.valeriopontini.catering.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import com.valeriopontini.catering.model.Role;
import com.valeriopontini.catering.model.CustomAuthentication;
import com.valeriopontini.catering.service.RoleService;



@Controller
public class HomeController {

	@Autowired
	RoleService rs;
	
	
	@GetMapping("/index")
	public String getHome() {
		return "index.html";
	}
	
	@GetMapping("/")
	public String Home() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role role = rs.getRole((OAuth2User) auth.getPrincipal());
		if(role == null) {
			Role r = new Role();
			r.setUser((OAuth2User) auth.getPrincipal());
			r.setRuolo(Role.DEFAULT_ROLE);
			rs.save(r);
			SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication(auth, r));
			return "index.html";
		}
		SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication(auth, role));
		if(role.getRuolo().equals("ADMIN"))
			return "azioniList.html";
		return "accessoNonConsentito.html";
			
		
	}
	@GetMapping("/azioni")
	public String getAzioni() {
		return "azioniList.html";
	}
	
	
	

}
