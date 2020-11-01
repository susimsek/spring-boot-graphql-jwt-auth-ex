package com.spring.graphql.service.user;

import com.spring.graphql.model.User;
import com.spring.graphql.payload.request.LoginRequest;
import com.spring.graphql.payload.request.UserCreateRequest;
import com.spring.graphql.payload.response.JwtResponse;

import java.util.List;

public interface UserService {

    JwtResponse login(LoginRequest loginRequest);
    List<User> listUser();
    User getUser(String id);
    User createUser(UserCreateRequest userCreateRequest);
    Boolean deleteUser(String id);

}
