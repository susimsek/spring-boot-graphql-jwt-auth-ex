package com.spring.graphql.exception.role;

import com.spring.graphql.enums.RoleName;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.MessageFormat;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleNotFoundException extends RuntimeException{

    @NonNull Long id;

    @Override
    public String getMessage() {
        return MessageFormat.format("Role with Id ''{0}'' could not be found", id);
    }

}