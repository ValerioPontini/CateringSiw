package com.valeriopontini.catering.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


import com.valeriopontini.catering.model.Role;

public interface RoleRepository  extends CrudRepository<Role, Long> {

	public Optional<Role> findByAuthUser(String user);
}
