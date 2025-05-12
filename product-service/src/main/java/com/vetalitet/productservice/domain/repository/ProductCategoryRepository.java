package com.vetalitet.productservice.domain.repository;

import com.vetalitet.productservice.domain.model.ProductCategory;

import java.util.Optional;

public interface ProductCategoryRepository {
    ProductCategory save(ProductCategory productCategory);
    Optional<ProductCategory> findById(Long id);
}
