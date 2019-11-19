package com.codecool.stockapp;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.repository.UserRepository;
import com.codecool.stockapp.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StockAppApplication {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Trader trader;

    public static void main(String[] args) {
        SpringApplication.run(StockAppApplication.class, args);
    }

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
            trader.scanOpenOrders();
        };
    }
}
