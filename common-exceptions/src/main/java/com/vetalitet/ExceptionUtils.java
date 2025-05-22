package com.vetalitet;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class ExceptionUtils {

    public static <T> Function<ClientResponse, Mono<? extends Throwable>> mapToCommonException(Class<T> errorBodyClass) {
        return response -> response.bodyToMono(errorBodyClass)
                .map(error -> {
                    HttpStatusCode statusCode = response.statusCode();
                    HttpStatus status = (statusCode instanceof HttpStatus httpStatus)
                            ? httpStatus
                            : HttpStatus.valueOf(statusCode.value());

                    String message;
                    if (error instanceof ErrorResponse errResp) {
                        message = errResp.message(); // або .message() залежно від класу
                    } else {
                        message = error.toString();
                    }

                    return new CommonException(message, status);
                });
    }

}
