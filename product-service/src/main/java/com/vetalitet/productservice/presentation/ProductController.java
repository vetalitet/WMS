package com.vetalitet.productservice.presentation;

import com.vetalitet.dto.CommonUpdateProductStatusRequest;
import com.vetalitet.productservice.application.usecase.CreateProductUseCase;
import com.vetalitet.productservice.application.usecase.DeleteProductByIdUseCase;
import com.vetalitet.productservice.application.usecase.GetAllProductsUseCase;
import com.vetalitet.productservice.application.usecase.GetProductByIdUseCase;
import com.vetalitet.productservice.application.usecase.UpdateProductStatusUseCase;
import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.model.ProductStatus;
import com.vetalitet.productservice.presentation.request.CreateProductRequest;
import com.vetalitet.productservice.presentation.request.UpdateProductStatusRequest;
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
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final UpdateProductStatusUseCase updateProductStatusUseCase;

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

    @GetMapping("/{id}/exists")
    public ResponseEntity<Void> checkProductExists(@PathVariable Long id) {
        boolean exists = getProductByIdUseCase.existsById(id);
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deleteProductByIdUseCase.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody CommonUpdateProductStatusRequest request) {
        updateProductStatusUseCase.updateStatus(id, request.status());
        return ResponseEntity.noContent().build();
    }

}
