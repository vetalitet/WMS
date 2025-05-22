package com.vetalitet.productservice.presentation.request;

import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.model.ProductCategory;
import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;
    private Float price;
    private ProductCategoryDto productCategory;

    public Product toDomain() {
        return new Product(name, price, new ProductCategory(productCategory.id, productCategory.name));
    }

    @Data
    public static class ProductCategoryDto {
        private Long id;
        private String name;
    }

}
