package com.spring.graphql.repository;

import com.spring.graphql.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Boolean existsByName(String name);
}