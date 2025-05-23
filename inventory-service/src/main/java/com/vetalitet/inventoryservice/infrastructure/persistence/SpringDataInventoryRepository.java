package com.vetalitet.inventoryservice.infrastructure.persistence;

import com.vetalitet.inventoryservice.infrastructure.persistence.entity.InventoryEntity;
import com.vetalitet.inventoryservice.infrastructure.persistence.mappers.InventoryMapper;
import com.vetalitet.inventoryservice.domain.model.Inventory;
import com.vetalitet.inventoryservice.domain.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SpringDataInventoryRepository implements InventoryRepository {

    private final JpaInventoryRepository jpaInventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public void save(Inventory inventory) {
        jpaInventoryRepository.save(inventoryMapper.toEntity(inventory));
    }

    @Override
    public List<Inventory> getAll() {
        final List<InventoryEntity> inventoryEntityList = jpaInventoryRepository.findAll();
        return inventoryEntityList.stream().map(inventoryMapper::toDomain).toList();
    }

    @Override
    public void deleteInventoryByProductId(Long productId) {
        jpaInventoryRepository.deleteById(productId);
    }

}
