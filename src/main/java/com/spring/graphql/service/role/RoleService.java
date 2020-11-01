package com.spring.graphql.service.role;

import com.spring.graphql.model.Role;

import java.util.List;

public interface RoleService {
    
    List<Role> listRole();
    Role getRole(Long id);

}
