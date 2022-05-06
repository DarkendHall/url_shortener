package org.darkend.url_shortener.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Introspected
@ReflectiveAccess
public class Url {

    @NotBlank
    @Pattern(regexp = "https?:\\/\\/(www\\.[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\/.[a-zA-Z0-9/]*)?)?" +
            "([a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\.[a-zA-Z0-9()]{2,3})?([a-zA-Z0-9/]*))?", message =
            "You need to provide a valid url")
    private String url;

    public Url() {
    }

    public String getUrl() {
        return url;
    }

    public Url setUrl(String url) {
        this.url = url;
        return this;
    }
}
