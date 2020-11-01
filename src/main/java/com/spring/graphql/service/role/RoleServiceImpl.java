package com.spring.graphql.service.role;

import com.spring.graphql.exception.role.RoleNotFoundException;
import com.spring.graphql.model.Role;
import com.spring.graphql.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    @Override
    public List<Role> listRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }
}
