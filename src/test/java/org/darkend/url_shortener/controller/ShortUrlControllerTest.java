package org.darkend.url_shortener.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.repository.ShortUrlRepository;
import org.darkend.url_shortener.service.ShortUrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class ShortUrlControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    ObjectMapper mapper;

    @Inject
    private ShortUrlService service;

    @Inject
    private ShortUrlRepository repository;

    private final ShortUrl shortUrl = new ShortUrl("123abc", "http://localhost:8080/s/123abc",
            "http://localhost:8080/short");

    @BeforeEach
    void setUp() {
        when(service.getAllShortUrls()).thenReturn(List.of(shortUrl));
        when(service.createShortUrl(new Url("http://localhost.com/short"), "localhost:8080")).thenReturn(shortUrl);
        when(service.getShortUrl("123abc")).thenReturn(shortUrl);
        when(service.getShortUrl("1234ab")).thenThrow(EntityNotFoundException.class);
    }

    @Test
    void getAllShortUrls() throws JsonProcessingException {
        var response = client.toBlocking()
                .retrieve(HttpRequest.GET("/short")
                        .accept(MediaType.APPLICATION_JSON));

        var result = mapper.readValue(response, new TypeReference<List<ShortUrl>>() {
        });

        assertThat(result).isEqualTo(List.of(shortUrl));
    }

    @Test
    void createWithValidUrlShouldReturnCreatedShortUrl() throws JsonProcessingException {
        var response = client.toBlocking()
                .retrieve(HttpRequest.POST("/short", """
                                {
                                  "url": "http://localhost.com/short"
                                }
                                """)
                        .headers(Map.of("Host", "localhost:8080")));

        var result = mapper.readValue(response, ShortUrl.class);

        assertThat(result).isEqualTo(shortUrl);
    }

    @Test
    void createWithInvalidUrlShouldThrowException() {
        Assertions.setMaxStackTraceElementsDisplayed(500000);
        assertThatThrownBy(() -> client.toBlocking()
                .retrieve(HttpRequest.POST("/short", """
                        {
                          "url": "localhost"
                        }
                        """)));
    }

    @Test
    void getWithValidIdShouldNotThrowException() {
        when(service.getShortUrl("123abc")).thenReturn(new ShortUrl("123abc", "http://localhost:8080/s/123abc",
                "http://google.com"));

        assertThatNoException().isThrownBy(() -> client.toBlocking()
                .retrieve(HttpRequest.GET("/s/123abc")));
    }

    @Test
    void getWithInvalidIdShouldThrowException() {
        assertThatThrownBy(() -> client.toBlocking()
                .retrieve(HttpRequest.GET("/s/1234bc")));
    }

    @MockBean(ShortUrlService.class)
    ShortUrlService shortUrlService() {
        return mock(ShortUrlService.class);
    }

    @MockBean(ShortUrlRepository.class)
    ShortUrlRepository shortUrlRepository() {
        return mock(ShortUrlRepository.class);
    }

}
