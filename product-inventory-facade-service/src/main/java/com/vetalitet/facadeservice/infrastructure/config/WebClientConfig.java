package com.vetalitet.facadeservice.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WebClientConfig {

    @Bean
    @Qualifier("productClient")
    public RestClient productServiceClient() {
        return RestClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Bean
    @Qualifier("inventoryClient")
    public RestClient inventoryServiceClient() {
        return RestClient.builder().baseUrl("http://localhost:8082").build();
    }

}
