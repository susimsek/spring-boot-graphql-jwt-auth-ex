package com.spring.graphql.exception.user;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailAlreadyExistsException extends RuntimeException{

    @NonNull String email;

    @Override
    public String getMessage() {
        return MessageFormat.format("Email already exists with email ''{0}''", email);
    }
}