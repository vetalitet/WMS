package com.vetalitet.productservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductCategory {
    private Long id;
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }
}
