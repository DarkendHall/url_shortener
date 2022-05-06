package org.darkend.url_shortener.exception;

import java.util.List;

public record ExceptionInfo(String timestamp, int status, List<String> messages) {
}
