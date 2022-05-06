package org.darkend.url_shortener.entity;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

public class Url {

    @NotBlank
    @URL(regexp = "https?:\\/\\/(www\\.[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\/.[a-zA-Z0-9/]*)?)?" +
            "([a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\.[a-zA-Z0-9()]{2,3})?([a-zA-Z0-9/]*))?")
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
