package com.spring.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Tour;
import com.spring.graphql.repository.AgencyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class TourResolver implements GraphQLResolver<Tour> {

    AgencyRepository agencyRepository;


    public Agency getAgency(Tour tour) {
        Long agencyId = tour.getAgency().getId();
        return agencyRepository.findById(agencyId).orElseThrow(() -> new AgencyNotFoundException(agencyId));
    }
}
