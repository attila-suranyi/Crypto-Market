package com.codecool.stockapp.config;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DefaultUserConfiguration {

    @Autowired
    UserRepository userRepository;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            User defaultUser = User.builder()
                    .firstName("Satosi")
                    .lastName("Nakamoto")
                    .balance(1000000)
                    .email("satoshinakamoto@gmail.com")
                    .userName("satosi")
                    .password("Test123")
                    .build();


            userRepository.save(defaultUser);
        };
    }
}
