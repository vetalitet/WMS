package com.vetalitet.productservice.domain.mapper;

import com.vetalitet.dto.CommonProductStatus;
import com.vetalitet.productservice.domain.model.ProductStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductStatusMapper {

    public ProductStatus toDomain(CommonProductStatus productStatus) {
        return switch (productStatus) {
            case PENDING -> ProductStatus.PENDING;
            case ACTIVE -> ProductStatus.ACTIVE;
            case FAILED -> ProductStatus.FAILED;
        };
    }

}
