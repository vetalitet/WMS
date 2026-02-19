package com.vetalitet.productservice.infrastructure.persistence.repository;

import com.vetalitet.productservice.infrastructure.persistence.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    Optional<ProductCategoryEntity> findByIdAndName(Long id, String name);

}
