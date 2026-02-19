package com.vetalitet.facadeservice.presentation.request;

import com.vetalitet.facadeservice.infrastructure.dto.ProductStatus;

public class UpdateProductStatusRequest {
    private ProductStatus productStatus;

    public UpdateProductStatusRequest(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
