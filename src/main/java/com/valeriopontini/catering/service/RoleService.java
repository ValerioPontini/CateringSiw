package com.valeriopontini.catering.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.valeriopontini.catering.model.Role;
import com.valeriopontini.catering.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository rr;
	
	
	public Role getRole(OAuth2User user) {
		Optional<Role> result = rr.findByAuthUser(user.getName());
		return result.orElse(null);
	}
	
	public void save(Role r) {
		rr.save(r);
	}
}
