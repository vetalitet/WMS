package com.vetalitet.exception.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleProductCreationException(CommonException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Unexpected server error"));
    }

}
