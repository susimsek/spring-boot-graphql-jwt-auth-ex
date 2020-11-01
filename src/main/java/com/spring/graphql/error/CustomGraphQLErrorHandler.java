package com.spring.graphql.error;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> graphQLErrors) {

        List<GraphQLError> serverErrors = graphQLErrors.stream()
                .map(this::unwrapError)
                .collect(Collectors.toList());

        return serverErrors;
    }

    private GraphQLError unwrapError(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching unwrappedError = (ExceptionWhileDataFetching) error;
            return new GenericGraphQLError(unwrappedError.getException().getMessage());
        } else {
            return error;
        }
    }

}