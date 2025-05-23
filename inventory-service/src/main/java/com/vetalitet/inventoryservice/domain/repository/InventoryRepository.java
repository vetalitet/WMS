package com.vetalitet.inventoryservice.domain.repository;

import com.vetalitet.inventoryservice.domain.model.Inventory;

import java.util.List;

public interface InventoryRepository {
    void save(Inventory inventory);
    List<Inventory> getAll();
    void deleteInventoryByProductId(Long productId);
}
