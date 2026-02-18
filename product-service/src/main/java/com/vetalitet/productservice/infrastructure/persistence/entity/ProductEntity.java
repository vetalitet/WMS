package com.vetalitet.productservice.infrastructure.persistence.entity;

import com.vetalitet.productservice.domain.model.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "product_category_entity_id")
    private ProductCategoryEntity productCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

}
