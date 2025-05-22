package com.vetalitet.facadeservice.presentation.request;

import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.domain.model.ProductCategory;
import lombok.Data;

@Data
public class ProductInventoryRequest {
    private Long id;
    private String name;
    private Float price;
    private ProductCategoryDto productCategory;
    private Long quantity;

    public ProductInventoryRequest(String name, Float price, ProductCategoryDto productCategory, Long quantity) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.quantity = quantity;
    }

    public Product toDomain() {
        return new Product(name, price, productCategory.toDomain(), quantity);
    }

    @Data
    public static class ProductCategoryDto {
        private Long id;
        private String name;

        public ProductCategory toDomain() {
            return new ProductCategory(id, name);
        }
    }

}
