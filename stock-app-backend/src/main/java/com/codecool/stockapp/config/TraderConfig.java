package com.codecool.stockapp.config;

import com.codecool.stockapp.model.CryptoAPIService;
import com.codecool.stockapp.model.RemoteURLReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraderConfig {
    @Bean
    public CryptoAPIService createCryptoAPIService() {
        return new CryptoAPIService();
    }

    @Bean
    public  RemoteURLReader createRemoteURLReader(){
        return new RemoteURLReader();
    }
}
