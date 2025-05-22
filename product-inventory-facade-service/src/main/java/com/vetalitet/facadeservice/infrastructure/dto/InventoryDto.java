package com.vetalitet.facadeservice.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InventoryDto {
    private Long productId;
    private Long quantity;
}
