package com.vetalitet.facadeservice.application.service;

import com.vetalitet.exception.core.CommonException;
import com.vetalitet.facadeservice.domain.model.OperationStatus;
import com.vetalitet.facadeservice.domain.model.ProductOperation;
import com.vetalitet.facadeservice.domain.repository.ProductOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final ProductOperationRepository repository;

    @Transactional
    public ProductOperation reserve(UUID requestId) {
        return repository.findByRequestId(requestId)
                .map(this::handleExistingOperation)
                .orElseGet(() ->
                        repository.save(ProductOperation.newOperation(requestId))
                );
    }

    private ProductOperation handleExistingOperation(ProductOperation operation) {
        return switch (operation.getStatus()) {
            case COMPLETED -> throw new CommonException(
                    "Request already processed",
                    HttpStatus.CONFLICT
            );
            case IN_PROGRESS -> throw new CommonException(
                    "Request is still processing",
                    HttpStatus.CONFLICT
            );
            case FAILED, NEW -> operation;
        };
    }


    @Transactional
    public void markInProgress(Long operationId, Long productId) {
        ProductOperation operation = repository.findById(operationId)
                .orElseThrow();

        operation.setProductId(productId);
        operation.setStatus(OperationStatus.IN_PROGRESS);
        repository.save(operation);
    }

    @Transactional
    public void markCompleted(Long operationId) {
        ProductOperation operation = repository.findById(operationId)
                .orElseThrow();

        operation.setStatus(OperationStatus.COMPLETED);
        repository.save(operation);
    }

    @Transactional
    public void markFailed(Long operationId) {
        ProductOperation operation = repository.findById(operationId)
                .orElseThrow();

        operation.setStatus(OperationStatus.FAILED);
        repository.save(operation);
    }

}
