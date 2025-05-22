package com.vetalitet.facadeservice.application.usecase;

import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import com.vetalitet.facadeservice.infrastructure.mappers.Mapper;
import com.vetalitet.facadeservice.infrastructure.webclient.InventoryWebClient;
import com.vetalitet.facadeservice.infrastructure.webclient.ProductWebClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateProductWithInventoryUseCase {

    private final ProductWebClient productWebClient;
    private final InventoryWebClient inventoryWebClient;
    private final Mapper mapper;

    public Product createProduct(Product product) {
        final ProductDto productDto = mapper.toProductDto(product);
        final ProductDto createdProductDto = productWebClient.createProduct(productDto);
        final InventoryDto inventoryDto = new InventoryDto(createdProductDto.getId(), product.getQuantity());
        final InventoryDto createdInventoryDto = inventoryWebClient.createInventory(inventoryDto);
        final Product resultProduct = mapper.toDomain(createdProductDto);
        resultProduct.setQuantity(createdInventoryDto.getQuantity());
        return resultProduct;
    }

}
