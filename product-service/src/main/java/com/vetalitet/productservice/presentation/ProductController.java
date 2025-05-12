package com.vetalitet.productservice.presentation;

import com.vetalitet.productservice.application.usecase.CreateProductUseCase;
import com.vetalitet.productservice.application.usecase.GetAllProductsUseCase;
import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.presentation.dto.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        final Product product = createProductUseCase.create(createProductRequest.toDomain());
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = getAllProductsUseCase.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
