package com.vetalitet.productservice.application.usecase;

import com.vetalitet.exception.core.CommonException;
import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public boolean existsById(Long id) {
        return productRepository.findProductById(id).isPresent();
    }

    public Product getById(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new CommonException(
                        "Product not found",
                        HttpStatus.NOT_FOUND
                ));
    }
}
