package com.spring.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Role;
import com.spring.graphql.model.Tour;
import com.spring.graphql.model.User;
import com.spring.graphql.service.agency.AgencyService;
import com.spring.graphql.service.role.RoleService;
import com.spring.graphql.service.tour.TourService;
import com.spring.graphql.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    TourService tourService;
    AgencyService agencyService;
    UserService userService;
    RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Role> listRole() {
        return roleService.listRole();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> listUser() {
        return userService.listUser();
    }

    @PreAuthorize("isAuthenticated()")
    public List<Tour> listTour() {
        return tourService.listTour();
    }

    @PreAuthorize("isAuthenticated()")
    public List<Agency> listAgency() {
        return agencyService.listAgency();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Role getRole(Long id) {
        return roleService.getRole(id);
    }

    @PreAuthorize("isAuthenticated()")
    public User getUser(String id) {
        return userService.getUser(id);
    }

    @PreAuthorize("isAuthenticated()")
    public Tour getTour(Long id) {
        return tourService.getTour(id);
    }

    @PreAuthorize("isAuthenticated()")
    public Agency getAgency(Long id) {
        return agencyService.getAgency(id);
    }
}