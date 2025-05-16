package com.vetalitet.inventoryservice.application.usecase;

import com.vetalitet.CommonException;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import com.vetalitet.inventoryservice.domain.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@AllArgsConstructor
public class CreateInventoryUseCase {

    private final InventoryRepository inventoryRepository;
    private final WebClient productServiceClient;

    public Inventory createInventory(Inventory inventory) {
        boolean productExists = checkProductExists(inventory.getProductId());

        if (!productExists) {
            throw new CommonException("Product " + inventory.getProductId() + " doesnt exist", HttpStatus.BAD_REQUEST);
        }

        inventoryRepository.save(inventory);
        return inventory;
    }

    private boolean checkProductExists(Long productId) {
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
