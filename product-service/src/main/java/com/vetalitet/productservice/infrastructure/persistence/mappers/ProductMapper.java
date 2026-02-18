package com.vetalitet.productservice.infrastructure.persistence.mappers;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.model.ProductCategory;
import com.vetalitet.productservice.infrastructure.persistence.entity.ProductCategoryEntity;
import com.vetalitet.productservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                toDomain(entity.getProductCategory()),
                entity.getProductStatus()
        );
    }

    public ProductCategory toDomain(ProductCategoryEntity entity) {
        if (entity == null) return null;

        return new ProductCategory(
                entity.getId(),
                entity.getName()
        );
    }

    public ProductEntity toEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setProductCategory(toEntity(product.getProductCategory()));
        entity.setProductStatus(product.getProductStatus());
        return entity;
    }

    public ProductCategoryEntity toEntity(ProductCategory category) {
        if (category == null) return null;

        ProductCategoryEntity entity = new ProductCategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        return entity;
    }

}
