package com.vetalitet.productservice.domain.model;

import lombok.*;

@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private Float price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;

    public Product(Long id,
                   String name,
                   Float price,
                   ProductCategory productCategory,
                   ProductStatus productStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
    }

    public Product(String name, Float price, ProductCategory productCategory) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.productStatus = ProductStatus.PENDING;
    }

    public void markActive() {
        this.productStatus = ProductStatus.ACTIVE;
    }

    public void markFailed() {
        this.productStatus = ProductStatus.FAILED;
    }
}
