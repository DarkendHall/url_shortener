package org.darkend.url_shortener.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import javax.persistence.EntityNotFoundException;

@Produces
@Singleton
@Requires(classes = {EntityNotFoundException.class, ExceptionHandler.class})
public class EntityNotFoundExceptionHandler implements ExceptionHandler<EntityNotFoundException,
        HttpResponse<?>> {

    private final ExceptionMessage exceptionMessage;

    public EntityNotFoundExceptionHandler(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, EntityNotFoundException exception) {
        return HttpResponse.notFound(
                exceptionMessage.convert(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}
