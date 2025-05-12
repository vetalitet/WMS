package com.vetalitet.productservice.presentation.dto;

public class ProductCreationException extends RuntimeException {
    public ProductCreationException(String message) {
        super(message);
    }
}
