package org.darkend.url_shortener.exception;

public class HostUrlException extends RuntimeException {

    public HostUrlException() {
    }

    public HostUrlException(String message) {
        super(message);
    }

    public HostUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public HostUrlException(Throwable cause) {
        super(cause);
    }

    public HostUrlException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
