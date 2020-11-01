package com.spring.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.exception.role.RoleNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Role;
import com.spring.graphql.model.Tour;
import com.spring.graphql.model.User;
import com.spring.graphql.repository.AgencyRepository;
import com.spring.graphql.repository.RoleRepository;
import com.spring.graphql.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class UserResolver implements GraphQLResolver<Tour> {

    RoleRepository roleRepository;

    public Role getRole(User user) {
        Long roleId = user.getRole().getId();
        return roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));
    }
}
