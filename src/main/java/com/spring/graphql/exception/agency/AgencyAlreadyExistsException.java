package com.spring.graphql.exception.agency;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgencyAlreadyExistsException extends RuntimeException{

    @NonNull String name;

    @Override
    public String getMessage() {
        return MessageFormat.format("Agency already exists with name ''{0}''", name);
    }
}