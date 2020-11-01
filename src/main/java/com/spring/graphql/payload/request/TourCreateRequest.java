package com.spring.graphql.payload.request;

import com.spring.graphql.enums.TourType;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourCreateRequest {

    @NotBlank
    String name;

    @NotBlank
    String price;

    String duration;

    String description;


    TourType type;

    Long agency;
}