package com.vetalitet.productservice.application.usecase;

import com.vetalitet.dto.CommonProductStatus;
import com.vetalitet.exception.list.ProductNotFoundException;
import com.vetalitet.productservice.domain.mapper.ProductStatusMapper;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProductStatusUseCase {

    private final ProductRepository productRepository;
    private final ProductStatusMapper productStatusMapper;

    public void updateStatus(Long id, CommonProductStatus status) {
        var productStatus = productStatusMapper.toDomain(status);
        var product = productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setProductStatus(productStatus);
        productRepository.save(product);
    }
}
