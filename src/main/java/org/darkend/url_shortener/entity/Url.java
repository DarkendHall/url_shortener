package org.darkend.url_shortener.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Introspected
@ReflectiveAccess
public class Url {

    @NotBlank
    @Pattern(
            regexp = "https?:\\/\\/(www\\.[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\/.(\\/[\\w\\W]*)]*)?)" +
                    "?([a-zA-Z0-9@:%._\\\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\.[a-zA-Z0-9()]{2,3})?((\\/[\\w\\W]*)*))?",
            message = "You need to provide a valid url")
    private String url;

    public Url() {
    }

    public Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Url setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "Url{" +
                "url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url1 = (Url) o;
        return Objects.equals(url, url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
