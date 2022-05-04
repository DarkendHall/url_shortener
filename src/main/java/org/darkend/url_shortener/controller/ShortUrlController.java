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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class ShortUrlController {

    private final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping("short")
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrl createNewShortUrl(@RequestBody @Valid Url originalUrl, HttpServletResponse response,
                                      HttpServletRequest request) {
        var requestUri = request.getRemoteHost() + ":" + request.getLocalPort();
        ShortUrl savedUrl = service.createShortUrl(originalUrl, requestUri);
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
}
