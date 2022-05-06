package org.darkend.url_shortener.controller;

import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class ShortUrlController {

    private final String HOST_URL;

    private final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
        this.HOST_URL = System.getenv("HOST_URL");
        checkValidHost();
    }

    @ResponseBody
    @PostMapping("short")
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrl createNewShortUrl(@RequestBody @Valid Url originalUrl, HttpServletResponse response) {
        ShortUrl savedUrl = service.createShortUrl(originalUrl, HOST_URL);
        response.addHeader("Location", savedUrl.getShortenedUrl());
        return savedUrl;
    }

    @ResponseBody
    @GetMapping("short")
    public List<ShortUrl> getAllShortUrls() {
        return service.getAllShortUrls();
    }

    @GetMapping("s/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void getShortUrl(@PathVariable String id, HttpServletResponse response) {
        logger.debug("Received GET request, getting redirect url");
        var normalUrl = service.getShortUrl(id)
                .getNormalUrl();
        logger.debug("Redirect gotten");
        logger.debug("Starting redirect");
        response.addHeader("Location", normalUrl);
    }

    private void checkValidHost() {
        if (this.HOST_URL == null)
            throw new IllegalStateException("Environment variable HOST_URL is not set");
    }
}
