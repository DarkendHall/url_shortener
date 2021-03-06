package org.darkend.url_shortener.service;

import io.micronaut.context.annotation.Bean;
import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.repository.ShortUrlRepository;
import org.darkend.url_shortener.utility.IdGenerator;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("HttpUrlsUsage")
@Bean
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final Validator validator;
    private final IdGenerator idGenerator;

    public ShortUrlService(ShortUrlRepository repository, Validator validator, IdGenerator idGenerator) {
        this.repository = repository;
        this.validator = validator;
        this.idGenerator = idGenerator;
    }

    public ShortUrl createShortUrl(Url originalUrl, String hostUrl) {
        String generatedId;
        do {
            generatedId = idGenerator.generateId();
        } while (!repository.findById(generatedId)
                .equals(Optional.empty()));

        ShortUrl shortUrl = new ShortUrl(generatedId, generateShortUrl(hostUrl, generatedId),
                originalUrl.getUrl());
        validator.validate(shortUrl);
        return repository.save(shortUrl);
    }

    private String generateShortUrl(String hostUrl, String id) {
        return "http://" + hostUrl + "/s/" + id;
    }

    public List<ShortUrl> getAllShortUrls() {
        var iterableResult = repository.findAll();
        List<ShortUrl> resultList = new ArrayList<>();
        iterableResult.forEach(resultList::add);
        return resultList;
    }

    public ShortUrl getShortUrl(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity could not be found with id: " + id));
    }
}
