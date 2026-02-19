package com.vetalitet.facadeservice.infrastructure.persistence.repository;

import com.vetalitet.facadeservice.infrastructure.persistence.entity.ProductOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaProductOperationRepository extends JpaRepository<ProductOperationEntity, Long> {
    Optional<ProductOperationEntity> findByRequestId(UUID requestId);
}
