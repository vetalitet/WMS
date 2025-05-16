package com.vetalitet.productservice.application.usecase;

import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public boolean existsById(Long id) {
        return productRepository.findById(id).isPresent();
    }

}
