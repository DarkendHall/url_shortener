package org.darkend.url_shortener.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {HostUrlException.class, ExceptionHandler.class})
public class HostUrlExceptionHandler implements ExceptionHandler<HostUrlException,
        HttpResponse<?>> {

    private final ExceptionMessage exceptionMessage;

    public HostUrlExceptionHandler(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, HostUrlException exception) {
        return HttpResponse.serverError(
                exceptionMessage.convert(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }
}
