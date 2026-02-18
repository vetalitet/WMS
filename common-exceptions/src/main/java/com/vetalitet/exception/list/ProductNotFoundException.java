package com.vetalitet.exception.list;

import com.vetalitet.exception.core.CommonException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CommonException {

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

}
