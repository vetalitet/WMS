package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.ErrorResponse;
import com.vetalitet.ExceptionUtils;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductWebClient {

    private final WebClient webClient;

    public ProductWebClient(@Qualifier("productClient") WebClient productServiceClient) {
        this.webClient = productServiceClient;
    }

    public ProductDto createProduct(ProductDto productDto) {
        return webClient
                .post()
                .uri("/api/products")
                .bodyValue(productDto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ExceptionUtils.mapToCommonException(ErrorResponse.class))
                .bodyToMono(ProductDto.class)
                .block();
    }

}
