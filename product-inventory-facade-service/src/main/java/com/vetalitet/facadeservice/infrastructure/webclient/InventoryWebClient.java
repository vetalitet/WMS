package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryWebClient {

    private final RestClient webClient;

    public InventoryWebClient(@Qualifier("inventoryClient") RestClient webClient) {
        this.webClient = webClient;
    }

    public InventoryDto createInventory(InventoryDto inventoryDto) {
        return webClient
                .post()
                .uri("/api/inventory")
                .body(inventoryDto)
                .retrieve()
                .body(InventoryDto.class);
    }

    public void deleteInventoryByProductId(Long productId) {

    }

}
