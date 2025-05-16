package com.vetalitet.inventoryservice.infrastructure.persistence.mappers;

import com.vetalitet.inventoryservice.infrastructure.persistence.entity.InventoryEntity;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryEntity toEntity(final Inventory inventory) {
        if (inventory == null) return null;

        final InventoryEntity entity = new InventoryEntity();
        entity.setProductId(inventory.getProductId());
        entity.setQuantity(inventory.getQuantity());
        return entity;
    }

    public Inventory toDomain(final InventoryEntity entity) {
        if (entity == null) return null;

        return new Inventory(entity.getProductId(), entity.getQuantity());
    }

}
