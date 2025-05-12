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

    public Product(String name, ProductCategory productCategory) {
        this.name = name;
        this.productCategory = productCategory;
    }
}
