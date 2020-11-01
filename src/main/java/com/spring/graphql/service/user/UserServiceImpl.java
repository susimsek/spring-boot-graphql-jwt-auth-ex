package com.spring.graphql.service.user;

import com.spring.graphql.enums.RoleName;
import com.spring.graphql.exception.role.RoleNameNotFoundException;
import com.spring.graphql.exception.user.EmailAlreadyExistsException;
import com.spring.graphql.exception.user.UserNotFoundException;
import com.spring.graphql.exception.user.UsernameAlreadyExistsException;
import com.spring.graphql.model.Role;
import com.spring.graphql.model.User;
import com.spring.graphql.payload.request.LoginRequest;
import com.spring.graphql.payload.request.UserCreateRequest;
import com.spring.graphql.payload.response.JwtResponse;
import com.spring.graphql.repository.RoleRepository;
import com.spring.graphql.repository.UserRepository;
import com.spring.graphql.security.UserDetailsImpl;
import com.spring.graphql.security.util.JwtTokenUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final ModelMapper modelMapper;
    final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateJwtToken(userPrincipal);

            return new JwtResponse(jwt);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(userCreateRequest.getUsername());
        }

        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new EmailAlreadyExistsException(userCreateRequest.getEmail());
        }

        User user=modelMapper.map(userCreateRequest, User.class);
        if(user.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        Role role = roleRepository.findByName(RoleName.USER).orElseThrow(() -> new RoleNameNotFoundException(RoleName.USER));

        user.setRole(role);

        user=userRepository.save(user);
        return user;
    }

    @Override
    public Boolean deleteUser(String id) {
       User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       userRepository.delete(user);
       return true;
    }
}
