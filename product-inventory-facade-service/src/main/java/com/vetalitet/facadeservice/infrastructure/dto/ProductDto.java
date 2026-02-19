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
    private ProductStatus productStatus;

    public ProductDto(String name, Float price, ProductCategoryDto productCategoryDto, ProductStatus productStatus) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategoryDto;
        this.productStatus = productStatus;
    }

}
