package com.vetalitet.productservice.domain.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private Float price;
    private ProductCategory productCategory;

    public Product(String name, Float price, ProductCategory productCategory) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
    }
}
