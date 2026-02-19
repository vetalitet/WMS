package com.vetalitet.facadeservice.domain.repository;

import com.vetalitet.facadeservice.domain.model.OperationStatus;
import com.vetalitet.facadeservice.domain.model.ProductOperation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductOperationRepository {

    ProductOperation save(ProductOperation operation);
    Optional<ProductOperation> findById(Long id);
    Optional<ProductOperation> findByRequestId(UUID requestId);
    List<ProductOperation> findByStatus(OperationStatus status);
    void updateStatus(UUID requestId, OperationStatus status);

}
