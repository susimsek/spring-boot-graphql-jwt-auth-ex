package com.spring.graphql.service.agency;

import com.spring.graphql.model.Agency;
import com.spring.graphql.payload.request.AgencyCreateRequest;
import com.spring.graphql.payload.request.AgencyUpdateRequest;

import java.util.List;

public interface AgencyService {

    List<Agency> listAgency();
    Agency getAgency(Long id);
    Agency createAgency(AgencyCreateRequest input);
    Agency updateAgency(Long id, AgencyUpdateRequest input);
    Boolean deleteAgency(Long id);

}
