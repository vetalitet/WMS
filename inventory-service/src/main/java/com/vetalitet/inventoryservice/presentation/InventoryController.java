package com.vetalitet.inventoryservice.presentation;

import com.vetalitet.inventoryservice.application.usecase.CreateInventoryUseCase;
import com.vetalitet.inventoryservice.application.usecase.DeleteInventoryUseCase;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import com.vetalitet.inventoryservice.domain.repository.InventoryRepository;
import com.vetalitet.inventoryservice.presentation.dto.CreateInventoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    private final CreateInventoryUseCase createInventoryUseCase;
    private final DeleteInventoryUseCase deleteInventoryUseCase;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody CreateInventoryRequest createInventoryRequest) {
        final Inventory inventory = createInventoryUseCase.createInventory(createInventoryRequest.toDomain());
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/api/inventory/{id}")
    public ResponseEntity<Void> deleteInventoryByProductId(@PathVariable Long id) {
        deleteInventoryUseCase.deleteInventoryByProductId(id);
        return ResponseEntity.noContent().build();
    }

}
