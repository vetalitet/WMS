package com.vetalitet.inventoryservice.application.usecase;

import com.vetalitet.inventoryservice.domain.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public void deleteInventoryByProductId(Long productId) {
        inventoryRepository.deleteInventoryByProductId(productId);
    }

}
