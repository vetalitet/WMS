package com.vetalitet.productservice.presentation.dto;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.model.ProductCategory;
import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;
    private ProductCategoryDto productCategoryDto;

    public Product toDomain() {
        return new Product(name, new ProductCategory(productCategoryDto.id, productCategoryDto.name));
    }

    @Data
    public static class ProductCategoryDto {
        private Long id;
        private String name;
    }

}
