package com.codecool.stockapp.config;

import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraderConfig {

    @Bean
    public CurrencyAPIService getCurrencyAPIService() {
        return new CurrencyAPIService();
    }
}
