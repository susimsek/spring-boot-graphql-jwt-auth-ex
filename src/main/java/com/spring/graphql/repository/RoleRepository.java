package com.spring.graphql.repository;


import com.spring.graphql.enums.RoleName;
import com.spring.graphql.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}