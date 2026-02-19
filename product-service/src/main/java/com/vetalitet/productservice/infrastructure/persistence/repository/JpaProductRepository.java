package com.vetalitet.productservice.infrastructure.persistence.repository;

import com.vetalitet.productservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
