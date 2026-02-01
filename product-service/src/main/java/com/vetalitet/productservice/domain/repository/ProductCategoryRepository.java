package com.vetalitet.productservice.domain.repository;

import com.vetalitet.productservice.domain.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {
    ProductCategory save(ProductCategory productCategory);
    List<ProductCategory> saveAll(List<ProductCategory> categories);
    Optional<ProductCategory> findById(Long id);
    long count();
}
