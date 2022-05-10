package org.darkend.url_shortener.controller;

import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.service.ShortUrlService;
import org.darkend.url_shortener.utility.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortUrlController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JsonConverter.class)
class ShortUrlControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShortUrlService service;

    private ShortUrl shortUrl;

    @BeforeEach
    void setUp() {
        shortUrl = new ShortUrl("123abc", "http:///s/123abc", "http://test.test");

        when(service.createShortUrl(any(Url.class), anyString())).thenReturn(shortUrl);
        when(service.getShortUrl("123abc")).thenReturn(shortUrl);
        when(service.getAllShortUrls()).thenReturn(List.of(shortUrl));
    }

    @Test
    void postShouldCreateNewShortUrl() throws Exception {
        mvc.perform(post("/short").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "url": "http://test.com"
                                }
                                """))
                .andExpect(jsonPath("$.id").value(shortUrl.getId()))
                .andExpect(jsonPath("$.normalUrl").value(shortUrl.getNormalUrl()))
                .andExpect(jsonPath("$.shortenedUrl").value(shortUrl.getShortenedUrl()))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllShouldReturnListOfAllShortUrls() throws Exception {
        mvc.perform(get("/short").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(shortUrl.getId()))
                .andExpect(jsonPath("$[0].normalUrl").value(shortUrl.getNormalUrl()))
                .andExpect(jsonPath("$[0].shortenedUrl").value(shortUrl.getShortenedUrl()))
                .andExpect(status().isOk());

    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public Clock clock() {
            return Clock.systemDefaultZone();
        }
    }
}
