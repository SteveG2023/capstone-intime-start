package com.example.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdresseUserConfig {

    @Bean
    public AdresseUser adresseUser() {
        return new AdresseUser();
    }
}