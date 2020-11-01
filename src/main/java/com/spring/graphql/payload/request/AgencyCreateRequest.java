package com.spring.graphql.payload.request;

import com.spring.graphql.enums.TourType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgencyCreateRequest {

    @NotBlank
    String name;

    Double rating;
}