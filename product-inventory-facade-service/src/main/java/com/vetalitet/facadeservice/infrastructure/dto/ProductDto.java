package com.vetalitet.facadeservice.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Float price;
    private ProductCategoryDto productCategory;

    public ProductDto(String name, Float price, ProductCategoryDto productCategoryDto) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategoryDto;
    }

}
