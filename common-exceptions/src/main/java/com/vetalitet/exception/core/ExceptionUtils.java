package com.vetalitet.exception.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static <T> CommonException toCommonException(
            HttpStatusCode statusCode,
            T errorBody
    ) {
        HttpStatus status = (statusCode instanceof HttpStatus httpStatus)
                ? httpStatus
                : HttpStatus.valueOf(statusCode.value());

        String message;
        if (errorBody instanceof ErrorResponse errResp) {
            message = errResp.message();
        } else if (errorBody != null) {
            message = errorBody.toString();
        } else {
            message = "Unknown error";
        }

        return new CommonException(message, status);
    }
}
