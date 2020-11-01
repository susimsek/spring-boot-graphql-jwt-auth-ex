package com.spring.graphql.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank
    @Size(max = 100)
    String username;

    @Size(max = 120)
    String password;


    @Size(max = 50)
    @Email
    String email;
}