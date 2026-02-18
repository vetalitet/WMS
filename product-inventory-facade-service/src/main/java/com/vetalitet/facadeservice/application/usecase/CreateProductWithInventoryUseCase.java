package com.vetalitet.facadeservice.application.usecase;

import com.vetalitet.dto.CommonProductStatus;
import com.vetalitet.facadeservice.application.service.OperationService;
import com.vetalitet.facadeservice.domain.model.Product;
import com.vetalitet.facadeservice.infrastructure.dto.InventoryDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductDto;
import com.vetalitet.facadeservice.infrastructure.dto.ProductStatus;
import com.vetalitet.facadeservice.infrastructure.mappers.Mapper;
import com.vetalitet.facadeservice.infrastructure.webclient.InventoryWebClient;
import com.vetalitet.facadeservice.infrastructure.webclient.ProductWebClient;
import com.vetalitet.facadeservice.saga.SagaExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateProductWithInventoryUseCase {

    private final ProductWebClient productWebClient;
    private final InventoryWebClient inventoryWebClient;
    private final OperationService operationService;
    private final SagaExecutor sagaExecutor;
    private final Mapper mapper;

    public Product createProduct(UUID requestId, Product product) {
        var operation = operationService.reserve(requestId);
        try {
            final ProductDto createdProduct = createPendingProduct(product);
            operationService.markInProgress(operation.getOperationId(), createdProduct.getId());
            executeSaga(createdProduct.getId(), product.getQuantity());
            markProductActive(createdProduct.getId());
            operationService.markCompleted(operation.getOperationId());
            return buildResultProduct(createdProduct, product.getQuantity());
        } catch (Exception ex) {
            if (operation.getProductId() != null) {
                markProductFailed(operation.getProductId());
            }
            operationService.markFailed(operation.getOperationId());
            throw ex;
        }
    }

    private ProductDto createPendingProduct(Product product) {
        ProductDto dto = mapper.toProductDto(product);
        dto.setProductStatus(ProductStatus.PENDING);
        return productWebClient.createProduct(dto);
    }

    private void executeSaga(Long productId, long quantity) {
        sagaExecutor
                .addStep(
                        () -> inventoryWebClient.createInventory(
                                new InventoryDto(productId, quantity)
                        ),
                        () -> markProductFailed(productId)
                )
                .execute();
    }

    private void markProductActive(Long productId) {
        productWebClient.updateStatus(productId, CommonProductStatus.ACTIVE);
    }

    private void markProductFailed(Long productId) {
        productWebClient.updateStatus(productId, CommonProductStatus.FAILED);
    }

    private Product buildResultProduct(ProductDto dto, long quantity) {
        Product result = mapper.toDomain(dto);
        result.setQuantity(quantity);
        return result;
    }

}
