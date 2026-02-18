package com.vetalitet.inventoryservice.infrastructure.persistence.repository;

import com.vetalitet.inventoryservice.infrastructure.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
