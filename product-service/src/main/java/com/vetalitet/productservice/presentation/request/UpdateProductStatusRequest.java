package com.vetalitet.productservice.presentation.request;

import com.vetalitet.productservice.domain.model.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStatusRequest {
    private ProductStatus status;
}
