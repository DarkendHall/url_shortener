package org.darkend.url_shortener.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.exception.EnvironmentVariableException;
import org.darkend.url_shortener.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class ShortUrlController {

    private final String HOST_URL;

    private final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
        this.HOST_URL = System.getenv("HOST_URL");
        checkValidHostUrl();
    }

    @Post("short")
    public HttpResponse<ShortUrl> createNewShortUrl(@Body @Valid Url originalUrl) {
        ShortUrl savedUrl = service.createShortUrl(originalUrl, HOST_URL);
        return HttpResponse.created(savedUrl)
                .header("Location", savedUrl.getShortenedUrl());
    }

    @Get("short")
    public List<ShortUrl> getAllShortUrls() {
        return service.getAllShortUrls();
    }

    @Get("s/{id}")
    public HttpResponse<Void> getShortUrl(@PathVariable String id) {
        logger.debug("Received GET request, getting redirect url");
        var normalUrl = service.getShortUrl(id)
                .getNormalUrl();
        logger.debug("Redirect gotten");
        logger.debug("Starting redirect");
        return HttpResponse.redirect(URI.create(normalUrl));
    }

    private void checkValidHostUrl() {
        if (this.HOST_URL == null)
            throw new EnvironmentVariableException("HOST_URL environment variable not set");
    }
}
