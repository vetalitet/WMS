package com.vetalitet.inventoryservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    public WebClient productServiceClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

}
