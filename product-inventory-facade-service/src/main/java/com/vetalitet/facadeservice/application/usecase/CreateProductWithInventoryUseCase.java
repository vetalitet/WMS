package com.vetalitet.facadeservice.application.usecase;

import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import com.vetalitet.facadeservice.infrastructure.mappers.Mapper;
import com.vetalitet.facadeservice.infrastructure.webclient.InventoryWebClient;
import com.vetalitet.facadeservice.infrastructure.webclient.ProductWebClient;
import com.vetalitet.facadeservice.saga.SagaExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@AllArgsConstructor
public class CreateProductWithInventoryUseCase {

    private final ProductWebClient productWebClient;
    private final InventoryWebClient inventoryWebClient;
    private final Mapper mapper;

    public Product createProduct(Product product) {
        final AtomicReference<ProductDto> createdProductDto = new AtomicReference<>();
        final AtomicReference<InventoryDto> createdInventoryDto = new AtomicReference<>();

        new SagaExecutor()
                .addStep(
                        () -> {
                            final ProductDto productDto = mapper.toProductDto(product);
                            createdProductDto.set(productWebClient.createProduct(productDto));
                        }, () -> {
                            final ProductDto productDto = createdProductDto.get();
                            if (productDto != null) {
                                productWebClient.deleteProductById(productDto.getId());
                            }
                        })
                .addStep(
                        () -> {
                            final InventoryDto inventoryDto
                                    = new InventoryDto(createdProductDto.get().getId(), product.getQuantity());
                            createdInventoryDto.set(inventoryWebClient.createInventory(inventoryDto));
                        }, () -> {
                            final InventoryDto inventoryDto = createdInventoryDto.get();
                            if (inventoryDto != null) {
                                inventoryWebClient.deleteInventoryByProductId(inventoryDto.getProductId());
                            }
                        })
                .execute();

        final Product resultProduct = mapper.toDomain(createdProductDto.get());
        resultProduct.setQuantity(createdInventoryDto.get().getQuantity());
        return resultProduct;
    }

}
