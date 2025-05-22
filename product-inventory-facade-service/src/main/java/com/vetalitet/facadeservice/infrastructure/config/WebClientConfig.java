package com.vetalitet.facadeservice.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    @Qualifier("productClient")
    public WebClient productServiceClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Bean
    @Qualifier("inventoryClient")
    public WebClient inventoryServiceClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }

}
