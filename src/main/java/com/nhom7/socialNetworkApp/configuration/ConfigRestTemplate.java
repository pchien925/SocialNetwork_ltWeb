package com.nhom7.socialNetworkApp.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigRestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}