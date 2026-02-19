package com.vetalitet.productservice.domain.repository;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> getAll();
    Optional<Product> findProductById(Long id);
    Optional<ProductCategory> findProductCategoryById(Long id);
    void deleteProductById(Long id);
}
