package com.vetalitet.productservice.application.usecase;

import com.vetalitet.exception.core.CommonException;
import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public Product create(Product product) {
        final var category = productRepository
                .findProductCategoryById(product.getProductCategory().getId())
                .orElseThrow(() -> new CommonException("Unknown product category", HttpStatus.BAD_REQUEST));
        product.setProductCategory(category);
        return productRepository.save(product);
    }

}
