package com.vetalitet.facadeservice.infrastructure.mappers;

import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.domain.model.ProductCategory;
import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductCategoryDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Product toDomain(ProductDto entity) {
        if (entity == null) return null;

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                toDomain(entity.getProductCategory()),
                0L
        );
    }

    public ProductDto toProductDto(Product domain) {
        if (domain == null) return null;

        return new ProductDto(
                domain.getName(),
                domain.getPrice(),
                toProductDto(domain.getProductCategory())
        );
    }

    public ProductCategory toDomain(ProductCategoryDto entity) {
        if (entity == null) return null;

        return new ProductCategory(
                entity.getId(),
                entity.getName()
        );
    }

    public ProductCategoryDto toProductDto(ProductCategory domain) {
        if (domain == null) return null;

        return new ProductCategoryDto(
                domain.getId(),
                domain.getName()
        );
    }

    public InventoryDto toInventoryDto(Product product) {
        if (product == null) return null;

        return new InventoryDto(
                product.getId(),
                product.getQuantity()
        );
    }
}
