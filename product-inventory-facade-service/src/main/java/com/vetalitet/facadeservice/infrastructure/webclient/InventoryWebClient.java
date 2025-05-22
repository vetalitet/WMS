package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InventoryWebClient {

    private final WebClient webClient;

    public InventoryWebClient(@Qualifier("inventoryClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public InventoryDto createInventory(InventoryDto inventoryDto) {
        return webClient
                .post()
                .uri("/api/inventory")
                .bodyValue(inventoryDto)
                .retrieve()
                .bodyToMono(InventoryDto.class)
                .block();
    }

}
