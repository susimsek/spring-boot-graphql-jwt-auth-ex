package com.spring.graphql.repository;

import com.spring.graphql.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findByAgency_Id(Long agency);
}