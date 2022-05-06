package org.darkend.url_shortener.exception;

import io.micronaut.http.HttpStatus;

public record ExceptionMessageData(String timestamp, HttpStatus status, String message) {
}
