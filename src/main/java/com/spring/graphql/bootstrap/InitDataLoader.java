package com.spring.graphql.bootstrap;


import com.spring.graphql.enums.RoleName;
import com.spring.graphql.enums.TourType;
import com.spring.graphql.exception.agency.AgencyNotFoundException;
import com.spring.graphql.exception.role.RoleNameNotFoundException;
import com.spring.graphql.model.Agency;
import com.spring.graphql.model.Role;
import com.spring.graphql.model.Tour;
import com.spring.graphql.model.User;
import com.spring.graphql.repository.AgencyRepository;
import com.spring.graphql.repository.RoleRepository;
import com.spring.graphql.repository.TourRepository;
import com.spring.graphql.repository.UserRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InitDataLoader implements CommandLineRunner {

    @NonNull TourRepository tourRepository;
    @NonNull AgencyRepository agencyRepository;

    @NonNull RoleRepository roleRepository;

    @NonNull UserRepository userRepository;

    @NonNull PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    String adminUsername;

    @Value("${admin.password}")
    String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findAll().isEmpty()){
            initRole();
        }

        if(userRepository.findAll().isEmpty()){
            initAdminUser();

        }

        if(agencyRepository.findAll().isEmpty()){
            initAgency();
        }

        if(tourRepository.findAll().isEmpty()){
            initTour();

        }

    }

    private void initAgency(){
        Agency lAgency = new Agency(1L, "AtoZTours", 5d);
        agencyRepository.save(lAgency);

    }

    private void initTour(){
        Agency lAgency = agencyRepository.findById(1L).orElseThrow(() -> new AgencyNotFoundException(1L));
        Stream.of(
                new Tour(1L, "Trondheim", "233", "2",
                        "Trondheim Kunstmuseum is an art museum located in Trondheim in Sør-Trøndelag county, Norway.", TourType.ECONOMY, lAgency),
                new Tour(2L, "Nidaros Cathedral", "100", "1",
                        "Nidaros Cathedral is a Church of Norway cathedral located in the city of Trondheim in Trøndelag county, Norway", TourType.COUPLE, lAgency),
                new Tour(3L, "Nidelva", "343", "2",
                        "Nidelva is a river in Trøndelag county, Norway. The 30-kilometre long river travels through the municipalities of Trondheim and Klæbu.", TourType.ADVENTURE, lAgency))
                .forEach(tourRepository::save);

    }

    private void initRole(){
        Role roleAdmin=new Role(RoleName.USER);
        Role roleUser=new Role(RoleName.ADMIN);

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

    }

    private void initAdminUser(){

        User user = new User();
        user.setUsername(adminUsername);
        user.setEmail("meet.admin@gmail.com");
        user.setPassword(passwordEncoder.encode(adminPassword));

        Role role = roleRepository.findByName(RoleName.ADMIN)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ADMIN));

        user.setRole(role);

        userRepository.save(user);
    }


}
