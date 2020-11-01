package com.spring.graphql.service.tour;

import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.exception.tour.TourNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Tour;
import com.spring.graphql.payload.request.TourCreateRequest;
import com.spring.graphql.payload.request.TourUpdateRequest;
import com.spring.graphql.repository.AgencyRepository;
import com.spring.graphql.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    final AgencyRepository agencyRepository;
    final TourRepository tourRepository;
    final ModelMapper modelMapper;

    @Override
    public List<Tour> listTour() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getTour(Long id) {
        return tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    }

    @Override
    public Tour createTour(TourCreateRequest input) {
        Agency agency = agencyRepository.findById(input.getAgency()).orElseThrow(() -> new AgencyNotFoundException(input.getAgency()));
        Tour tour = modelMapper.map(input,Tour.class);
        tour.setAgency(agency);
        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Long id, TourUpdateRequest input) {
        Agency agency = agencyRepository.findById(input.getAgency()).orElseThrow(() -> new AgencyNotFoundException(input.getAgency()));
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
        String name = input.getName();
        String price = input.getPrice();
        if (StringUtils.isNotEmpty(name)) tour.setName(name);
        if (StringUtils.isNotEmpty(price)) tour.setPrice(price);
        if (input.getType() != null) tour.setType(input.getType());
        tour.setAgency(agency);

        return tourRepository.save(tour);
    }

    @Override
    public Boolean deleteTour(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
        tourRepository.delete(tour);
        return true;
    }
}
