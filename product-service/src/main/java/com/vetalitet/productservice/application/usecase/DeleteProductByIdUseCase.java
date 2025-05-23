package com.vetalitet.productservice.application.usecase;

import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteProductByIdUseCase {

    private final ProductRepository productRepository;

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

}
