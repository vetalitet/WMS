package com.vetalitet.productservice.infrastructure.persistence.entity;

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
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "product_category_entity_id")
    private ProductCategoryEntity productCategory;

}
