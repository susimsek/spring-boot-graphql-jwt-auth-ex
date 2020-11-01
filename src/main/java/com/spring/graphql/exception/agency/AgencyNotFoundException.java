package com.spring.graphql.exception.agency;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgencyNotFoundException extends RuntimeException{

    @NonNull Long id;

    @Override
    public String getMessage() {
        return MessageFormat.format("Agency with ID ''{0}'' could not be found", id);
    }

}