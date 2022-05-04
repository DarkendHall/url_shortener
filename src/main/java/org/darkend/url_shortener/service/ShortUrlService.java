package org.darkend.url_shortener.service;

import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.repository.ShortUrlRepository;
import org.darkend.url_shortener.utility.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("HttpUrlsUsage")
@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final Validator validator;

    public ShortUrlService(ShortUrlRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public ShortUrl createShortUrl(Url originalUrl, UriComponents uriComponents) {
        String generatedId;
        do {
            generatedId = IdGenerator.generateId();
        } while (!repository.findById(generatedId)
                .equals(Optional.empty()));

        ShortUrl shortUrl = new ShortUrl(generatedId, generateShortUrl(uriComponents, generatedId),
                originalUrl.getUrl());
        validator.validate(shortUrl);
        return repository.save(shortUrl);
    }

    private String generateShortUrl(UriComponents uriComponents, String id) {
        return "http://" + uriComponents.getHost() + ":" + uriComponents.getPort() + "/s/" + id;
    }

    public List<ShortUrl> getAllShortUrls() {
        return repository.findAll();
    }

    public ShortUrl getShortUrl(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity could not be found with id: " + id));
    }
}
