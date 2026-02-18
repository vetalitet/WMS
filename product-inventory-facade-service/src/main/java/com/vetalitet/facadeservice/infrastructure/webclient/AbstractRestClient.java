package com.vetalitet.facadeservice.infrastructure.webclient;

import com.vetalitet.exception.core.ErrorResponse;
import com.vetalitet.exception.core.ExceptionUtils;
import com.vetalitet.exception.list.DownstreamServiceUnavailableException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

import java.util.function.Supplier;

public abstract class AbstractRestClient {

    protected <T> T execute(Supplier<T> action, String serviceName) {
        try {
            return action.get();
        } catch (HttpStatusCodeException ex) {
            ErrorResponse body = ex.getResponseBodyAs(ErrorResponse.class);
            throw ExceptionUtils.toCommonException(
                    ex.getStatusCode(),
                    body
            );
        } catch (ResourceAccessException ex) {
            throw new DownstreamServiceUnavailableException(
                    serviceName + " service unavailable"
            );
        }
    }

}
