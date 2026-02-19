package com.vetalitet.exception.list;

import com.vetalitet.exception.core.CommonException;
import org.springframework.http.HttpStatus;

public class DownstreamServiceUnavailableException extends CommonException {

    public DownstreamServiceUnavailableException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
