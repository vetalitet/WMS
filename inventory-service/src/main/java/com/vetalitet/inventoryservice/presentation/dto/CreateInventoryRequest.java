package com.vetalitet.inventoryservice.presentation.dto;

import com.vetalitet.inventoryservice.domain.model.Inventory;
import lombok.Data;

@Data
public class CreateInventoryRequest {

    private Long productId;
    private Long quantity;

    public Inventory toDomain() {
        return new Inventory(productId, quantity);
    }

}
