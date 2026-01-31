package com.vetalitet.inventoryservice.infrastructure.webclient;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class ProductWebClient {

    private final RestClient productServiceClient;

    public boolean checkProductExists(Long productId) {
        try {
            productServiceClient
                    .get()
                    .uri("/api/products/{id}/exists", productId)
                    .retrieve()
                    .toBodilessEntity();

            return true;
        } catch (HttpStatusCodeException ex) {
            return ex.getStatusCode() != HttpStatus.NOT_FOUND;
        }
    }
}
