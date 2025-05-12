package com.vetalitet.productservice.application.usecase;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

}
