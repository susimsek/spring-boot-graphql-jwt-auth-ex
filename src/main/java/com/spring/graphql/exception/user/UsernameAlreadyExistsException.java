package com.spring.graphql.exception.user;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsernameAlreadyExistsException extends RuntimeException{

    @NonNull String username;

    @Override
    public String getMessage() {
        return MessageFormat.format("User already exists with username ''{0}''", username);
    }
}