package com.spring.graphql.exception.user;

import com.spring.graphql.enums.RoleName;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotFoundException extends RuntimeException{

    @NonNull String id;

    @Override
    public String getMessage() {
        return MessageFormat.format("User with Id ''{0}'' could not be found", id);
    }

}