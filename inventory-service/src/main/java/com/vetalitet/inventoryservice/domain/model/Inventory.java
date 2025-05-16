package com.vetalitet.inventoryservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    private Long productId;
    private Long quantity;
}
