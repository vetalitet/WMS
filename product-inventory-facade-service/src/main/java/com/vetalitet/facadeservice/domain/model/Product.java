package com.vetalitet.facadeservice.domain.model;

import com.vetalitet.facadeservice.infrastructure.dto.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private Float price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
    private Long quantity;

    public Product(String name, Float price, ProductCategory productCategory, Long quantity) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.productStatus = ProductStatus.PENDING;
        this.quantity = quantity;
    }
}
