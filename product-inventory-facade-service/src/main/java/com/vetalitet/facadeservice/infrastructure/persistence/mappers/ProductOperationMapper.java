package com.vetalitet.facadeservice.infrastructure.persistence.mappers;

import com.vetalitet.facadeservice.domain.model.ProductOperation;
import com.vetalitet.facadeservice.infrastructure.persistence.entity.ProductOperationEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductOperationMapper {

    public ProductOperationEntity toEntity(ProductOperation domain) {
        if (domain == null) {
            return null;
        }

        ProductOperationEntity entity = new ProductOperationEntity();
        entity.setOperationId(domain.getOperationId());
        entity.setRequestId(domain.getRequestId());
        entity.setProductId(domain.getProductId());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());

        return entity;
    }

    public ProductOperation toDomain(ProductOperationEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ProductOperation(
                entity.getOperationId(),
                entity.getRequestId(),
                entity.getProductId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

}
