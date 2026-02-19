package com.vetalitet.facadeservice.infrastructure.persistence.repository;

import com.vetalitet.facadeservice.domain.model.OperationStatus;
import com.vetalitet.facadeservice.domain.model.ProductOperation;
import com.vetalitet.facadeservice.domain.repository.ProductOperationRepository;
import com.vetalitet.facadeservice.infrastructure.persistence.entity.ProductOperationEntity;
import com.vetalitet.facadeservice.infrastructure.persistence.mappers.ProductOperationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SpringDataProductOperationRepository implements ProductOperationRepository {

    private final JpaProductOperationRepository jpaRepository;
    private final ProductOperationMapper mapper;

    @Override
    public ProductOperation save(ProductOperation operation) {
        final ProductOperationEntity entity = mapper.toEntity(operation);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<ProductOperation> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ProductOperation> findByRequestId(UUID requestId) {
        return jpaRepository.findByRequestId(requestId)
                .map(mapper::toDomain);
    }

    @Override
    public List<ProductOperation> findByStatus(OperationStatus status) {
        return List.of();
    }

    @Override
    public void updateStatus(UUID requestId, OperationStatus status) {
        final ProductOperationEntity entity =
                jpaRepository.findByRequestId(requestId)
                        .orElseThrow();
        entity.setStatus(status);
    }

}
