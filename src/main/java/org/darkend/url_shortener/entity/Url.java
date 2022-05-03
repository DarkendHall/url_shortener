package org.darkend.url_shortener.entity;

import javax.validation.constraints.NotBlank;

public class Url {

    @NotBlank
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
