package org.darkend.url_shortener.exception;

import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Singleton
public class ExceptionMessage {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Clock clock;

    public ExceptionMessage(Clock clock) {
        this.clock = clock;
    }

    public ExceptionMessageData convert(HttpStatus status, String msg) {
        return new ExceptionMessageData(LocalDateTime.now(clock)
                .format(dtf), status, msg);
    }

}
