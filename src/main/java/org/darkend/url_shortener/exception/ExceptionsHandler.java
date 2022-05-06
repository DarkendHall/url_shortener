package org.darkend.url_shortener.exception;

import org.darkend.url_shortener.utility.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandler {

    private final Clock clock;
    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final JsonConverter converter;

    public ExceptionsHandler(Clock clock, JsonConverter converter) {
        this.clock = clock;
        this.converter = converter;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.warn(e.getMessage());

        return new ResponseEntity<>(converter.convert(new ExceptionInfo(LocalDateTime.now(clock)
                .format(dtf), HttpStatus.NOT_FOUND.value(), List.of(e.getMessage()))), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        logger.warn(e.getMessage());

        return new ResponseEntity<>(converter.convert(new ExceptionInfo(LocalDateTime.now(clock)
                .format(dtf), HttpStatus.BAD_REQUEST.value(), List.of(e.getMessage()))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorList = e.getFieldErrors()
                .stream()
                .map(error -> String.format("Validation failed for field: '%s' %s, value was: %s",
                        error.getField(),
                        error.getDefaultMessage(), checkRejectedValue(error.getRejectedValue())))
                .toList();

        errorList.forEach(logger::warn);

        return new ResponseEntity<>(converter.convert(new ExceptionInfo(LocalDateTime.now(clock)
                .format(dtf), HttpStatus.BAD_REQUEST.value(), errorList)), HttpStatus.BAD_REQUEST);
    }

    private String checkRejectedValue(Object rejectedValue) {
        if (rejectedValue != null && rejectedValue.toString()
                .length() > 0)
            return rejectedValue.toString();
        else
            return "null";
    }
}
