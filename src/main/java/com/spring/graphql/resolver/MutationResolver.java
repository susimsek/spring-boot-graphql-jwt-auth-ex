package com.spring.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.exception.tour.TourNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Tour;
import com.spring.graphql.model.User;
import com.spring.graphql.payload.request.*;
import com.spring.graphql.payload.response.JwtResponse;
import com.spring.graphql.repository.AgencyRepository;
import com.spring.graphql.repository.TourRepository;
import com.spring.graphql.security.UserDetailsImpl;
import com.spring.graphql.service.agency.AgencyService;
import com.spring.graphql.service.tour.TourService;
import com.spring.graphql.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.loadtime.Agent;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@Validated
public class MutationResolver implements GraphQLMutationResolver {

    TourService tourService;
    AgencyService agencyService;
    UserService userService;

    @PreAuthorize("isAnonymous()")
    public JwtResponse login(@Valid LoginRequest input) {
        return userService.login(input);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public User createUser(@Valid UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }


    @PreAuthorize("isAuthenticated()")
    public Tour createTour(@Valid TourCreateRequest input) {
        return tourService.createTour(input);
    }


    @PreAuthorize("isAuthenticated()")
    public Agency createAgency(@Valid AgencyCreateRequest input) {

        return agencyService.createAgency(input);
    }

    @PreAuthorize("isAuthenticated()")
    public Agency updateAgency(Long id, @Valid AgencyUpdateRequest input) {
       return agencyService.updateAgency(id,input);
    }


    @PreAuthorize("isAuthenticated()")
    public Tour updateTour(Long id,@Valid TourUpdateRequest input) {
       return tourService.updateTour(id,input);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean deleteUser(String id) {
        return userService.deleteUser(id);
    }

    @PreAuthorize("isAuthenticated()")
    public Boolean deleteTour(Long id) {
       return tourService.deleteTour(id);
    }

    @PreAuthorize("isAuthenticated()")
    public Boolean deleteAgency(Long id) {
        return agencyService.deleteAgency(id);
    }




}