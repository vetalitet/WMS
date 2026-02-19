package com.vetalitet.facadeservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ProductOperation {

    private Long operationId;
    private UUID requestId;
    private Long productId;
    private OperationStatus status;
    private Instant createdAt;

    public static ProductOperation newOperation(UUID requestId) {
        return new ProductOperation(
                null,
                requestId,
                null,
                OperationStatus.NEW,
                null
        );
    }

}
