package com.vetalitet.inventoryservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WebClientConfig {

    @Bean
    public RestClient productServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

}
