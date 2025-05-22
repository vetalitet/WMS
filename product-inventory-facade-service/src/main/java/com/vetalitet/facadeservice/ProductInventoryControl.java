package com.vetalitet.facadeservice;

import com.vetalitet.CommonException;
import com.vetalitet.facadeservice.application.usecase.CreateProductWithInventoryUseCase;
import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.presentation.request.ProductInventoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-inventory")
@AllArgsConstructor
public class ProductInventoryControl {

    private CreateProductWithInventoryUseCase createProductWithInventoryUseCase;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductInventoryRequest productInventoryRequest) {
        try {
            final Product product = createProductWithInventoryUseCase.createProduct(
                    productInventoryRequest.toDomain()
            );
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            throw new CommonException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
