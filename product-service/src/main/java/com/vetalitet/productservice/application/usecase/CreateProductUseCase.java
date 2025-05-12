package com.vetalitet.productservice.application.usecase;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

}
