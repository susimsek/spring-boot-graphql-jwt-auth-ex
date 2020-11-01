package com.spring.graphql.exception.tour;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourNotFoundException extends RuntimeException {

    @NonNull Long id;

    @Override
    public String getMessage() {
        return MessageFormat.format("Tour with ID ''{0}'' could not be found", id);
    }
}