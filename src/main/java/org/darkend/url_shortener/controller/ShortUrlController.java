package org.darkend.url_shortener.controller;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.exception.HostUrlException;
import org.darkend.url_shortener.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller
public class ShortUrlController {

    private final String HOST_URL;
    private final boolean IS_HOST_SET;

    private final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    private final ShortUrlService service;

    public ShortUrlController(@Value("${host_url:}") String host_url, ShortUrlService service) {
        this.HOST_URL = host_url;
        this.service = service;
        this.IS_HOST_SET = checkValidHostUrl();
    }

    @Post("short")
    public HttpResponse<ShortUrl> createNewShortUrl(@Body @Valid Url originalUrl, HttpHeaders httpHeaders) {
        String hostUrl = "";
        if (httpHeaders.get("X-Host") != null && !Objects.equals(httpHeaders.get("Host"), ""))
            hostUrl = httpHeaders.get("X-Host");
        else if (IS_HOST_SET)
            hostUrl = HOST_URL;
        if (Objects.equals(hostUrl, ""))
            throw new HostUrlException("Unable to find host url, please make sure you're sending it as a Host " +
                    "header or set HOST_URL in the consul config");

        ShortUrl savedUrl = service.createShortUrl(originalUrl, hostUrl);

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

    private boolean checkValidHostUrl() {
        if (this.HOST_URL == null) {
            logger.warn("host_url not set in configuration files, expecting 'X-Host' header with each POST request");
            return false;
        }
        return true;
    }

}
