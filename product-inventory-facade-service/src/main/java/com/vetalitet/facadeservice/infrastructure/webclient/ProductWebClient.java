package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.ErrorResponse;
import com.vetalitet.ExceptionUtils;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

@Component
public class ProductWebClient {

    private final RestClient restClient;

    public ProductWebClient(@Qualifier("productClient") RestClient productServiceClient) {
        this.restClient = productServiceClient;
    }

    public ProductDto createProduct(ProductDto productDto) {
        try {
            return restClient.post()
                    .uri("/api/products")
                    .body(productDto)
                    .retrieve()
                    .body(ProductDto.class);
        } catch (HttpStatusCodeException ex) {
            ErrorResponse body = ex.getResponseBodyAs(ErrorResponse.class);
            throw ExceptionUtils.toCommonException(ex.getStatusCode(), body);
        }
    }

    public void deleteProductById(Long id) {
        try {
            restClient.delete()
                    .uri("/api/products/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpStatusCodeException ex) {
            throw ExceptionUtils.toCommonException(ex.getStatusCode(), null);
        }
    }

}
