package com.spring.graphql.service.agency;

import com.spring.graphql.exception.agency.AgencyAlreadyExistsException;
import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.payload.request.AgencyCreateRequest;
import com.spring.graphql.payload.request.AgencyUpdateRequest;
import com.spring.graphql.repository.AgencyRepository;
import com.spring.graphql.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    final AgencyRepository agencyRepository;
    final TourRepository tourRepository;
    final ModelMapper modelMapper;

    @Override
    public List<Agency> listAgency() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgency(Long id) {
        return agencyRepository.findById(id).orElseThrow(() -> new AgencyNotFoundException(id));
    }

    @Override
    public Agency createAgency(AgencyCreateRequest input) {
        if(agencyRepository.existsByName(input.getName())){
            throw new AgencyAlreadyExistsException(input.getName());
        }
        Agency agency = modelMapper.map(input,Agency.class);
        return agencyRepository.save(agency);
    }

    @Override
    public Agency updateAgency(Long id, AgencyUpdateRequest input) {
        Agency agency = agencyRepository.findById(id).orElseThrow(() -> new AgencyNotFoundException(id));
        agency.setRating(input.getRating());
        return agencyRepository.save(agency);
    }

    @Override
    public Boolean deleteAgency(Long id) {
        Agency agency = agencyRepository.findById(id).orElseThrow(() -> new AgencyNotFoundException(id));
        tourRepository.findByAgency_Id(id).forEach(tour -> tourRepository.delete(tour));
        agencyRepository.delete(agency);;
        return true;
    }
}
