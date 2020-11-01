package com.spring.graphql.service.tour;

import com.spring.graphql.model.Tour;
import com.spring.graphql.payload.request.AgencyCreateRequest;
import com.spring.graphql.payload.request.AgencyUpdateRequest;
import com.spring.graphql.payload.request.TourCreateRequest;
import com.spring.graphql.payload.request.TourUpdateRequest;

import java.util.List;

public interface TourService {

    List<Tour> listTour();
    Tour getTour(Long id);
    Tour createTour(TourCreateRequest input);
    Tour updateTour(Long id, TourUpdateRequest input);
    Boolean deleteTour(Long id);

}
