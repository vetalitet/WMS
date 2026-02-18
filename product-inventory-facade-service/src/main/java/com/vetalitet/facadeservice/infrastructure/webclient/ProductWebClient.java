package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.dto.CommonProductStatus;
import com.vetalitet.dto.CommonUpdateProductStatusRequest;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductWebClient extends AbstractRestClient {

    private final RestClient restClient;

    public ProductWebClient(@Qualifier("productClient") RestClient productServiceClient) {
        this.restClient = productServiceClient;
    }

    public ProductDto createProduct(ProductDto productDto) {
        return execute(() ->
                restClient.post()
                        .uri("/api/products")
                        .body(productDto)
                        .retrieve()
                        .body(ProductDto.class), "Product");
    }

    public void updateStatus(Long id, CommonProductStatus status) {
        execute(() -> {
            restClient.patch()
                    .uri("/api/products/{id}/status", id)
                    .body(new CommonUpdateProductStatusRequest(status))
                    .retrieve()
                    .toBodilessEntity();
            return null;
        }, "Product");
    }

}
