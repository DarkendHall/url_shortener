package org.darkend.url_shortener.exception;

public class EnvironmentVariableException extends RuntimeException {

    public EnvironmentVariableException() {
    }

    public EnvironmentVariableException(String message) {
        super(message);
    }

    public EnvironmentVariableException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvironmentVariableException(Throwable cause) {
        super(cause);
    }

    public EnvironmentVariableException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
