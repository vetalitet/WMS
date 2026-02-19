package com.vetalitet.inventoryservice.application.usecase;

import com.vetalitet.exception.core.CommonException;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import com.vetalitet.inventoryservice.domain.repository.InventoryRepository;
import com.vetalitet.inventoryservice.infrastructure.webclient.ProductWebClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateInventoryUseCase {

    private final InventoryRepository inventoryRepository;
    private final ProductWebClient productWebClient;

    public Inventory createInventory(Inventory inventory) {
        boolean productExists = productWebClient.checkProductExists(inventory.getProductId());

        if (!productExists) {
            throw new CommonException("Product " + inventory.getProductId() + " doesnt exist", HttpStatus.BAD_REQUEST);
        }

        inventoryRepository.save(inventory);
        return inventory;
    }

}
