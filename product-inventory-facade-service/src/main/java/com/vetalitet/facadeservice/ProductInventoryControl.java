package com.vetalitet.facadeservice;

import com.vetalitet.facadeservice.application.usecase.CreateProductWithInventoryUseCase;
import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.presentation.request.ProductInventoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product-inventory")
@AllArgsConstructor
public class ProductInventoryControl {

    private CreateProductWithInventoryUseCase createProductWithInventoryUseCase;

    @PostMapping()
    public ResponseEntity<Product> createProduct(
            @RequestHeader("X-Request-ID") UUID requestId,
            @RequestBody ProductInventoryRequest productInventoryRequest) {
        final Product product = createProductWithInventoryUseCase.createProduct(
                requestId,
                productInventoryRequest.toDomain()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
