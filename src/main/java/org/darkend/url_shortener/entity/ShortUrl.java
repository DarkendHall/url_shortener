package org.darkend.url_shortener.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity(name = "short_url")
@Introspected
@ReflectiveAccess
public class ShortUrl {

    @Id
    private String id;

    @NotBlank
    @Column(name = "shortened_url")
    private String shortenedUrl;

    @NotBlank
    @Column(name = "normal_url")
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

    @Override
    public String toString() {
        return "ShortUrl{" +
                "id='" + id + '\'' +
                ", shortenedUrl='" + shortenedUrl + '\'' +
                ", normalUrl='" + normalUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(id, shortUrl.id) && Objects.equals(shortenedUrl,
                shortUrl.shortenedUrl) && Objects.equals(normalUrl, shortUrl.normalUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortenedUrl, normalUrl);
    }
}
