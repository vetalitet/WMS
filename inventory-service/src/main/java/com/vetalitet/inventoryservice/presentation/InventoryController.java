package com.vetalitet.inventoryservice.presentation;

import com.vetalitet.inventoryservice.application.usecase.CreateInventoryUseCase;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import com.vetalitet.inventoryservice.presentation.dto.CreateInventoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    private final CreateInventoryUseCase createInventoryUseCase;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody CreateInventoryRequest createInventoryRequest) {
        final Inventory inventory = createInventoryUseCase.createInventory(createInventoryRequest.toDomain());
        return ResponseEntity.ok(inventory);
    }

}
