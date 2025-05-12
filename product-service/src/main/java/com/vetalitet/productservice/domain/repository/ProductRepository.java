package com.vetalitet.productservice.domain.repository;

import com.vetalitet.productservice.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> getAll();
    Optional<Product> findById(Long id);
}
