package com.vetalitet.inventoryservice.infrastructure.webclient;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@AllArgsConstructor
public class ProductWebClient {

    private final WebClient productServiceClient;

    public boolean checkProductExists(Long productId) {
        try {
            productServiceClient
                    .get()
                    .uri("/api/products/{id}/exists", productId)
                    .retrieve()
                    .toBodilessEntity()
                    .block(); // block for sync result
            return true;
        } catch (WebClientResponseException e) {
            return e.getStatusCode() != HttpStatus.NOT_FOUND;
        }
    }

}
