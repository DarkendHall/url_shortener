package org.darkend.url_shortener.entity;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Url {

    @NotBlank
    @URL(regexp = "https?:\\/\\/(www\\.[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\/.[a-zA-Z0-9/]*)?)?" +
            "([a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{2,3}(\\.[a-zA-Z0-9()]{2,3})?([a-zA-Z0-9/]*))?")
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
