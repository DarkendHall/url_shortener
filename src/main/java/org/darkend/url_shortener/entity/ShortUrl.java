package org.darkend.url_shortener.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Introspected
@ReflectiveAccess
public class ShortUrl {

    @Id
    private String id;

    @NotBlank
    private String shortenedUrl;

    @NotBlank
    private String normalUrl;

    public ShortUrl() {
    }

    public ShortUrl(String id, String shortenedUrl, String normalUrl) {
        this.id = id;
        this.shortenedUrl = shortenedUrl;
        this.normalUrl = normalUrl;
    }

    public String getId() {
        return id;
    }

    public ShortUrl setId(String id) {
        this.id = id;
        return this;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public ShortUrl setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
        return this;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public ShortUrl setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
        return this;
    }
}
