package com.spring.graphql.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.graphql.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    static final long serialVersionUID = 1L;

    @NonNull String id;

    @NonNull String username;

    @JsonIgnore
    @NonNull String password;

    @NonNull String email;

    @NonNull private Collection<? extends GrantedAuthority> authorities;

    Map<String, Object> attributes;


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName().name()));

        return new UserDetailsImpl(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}