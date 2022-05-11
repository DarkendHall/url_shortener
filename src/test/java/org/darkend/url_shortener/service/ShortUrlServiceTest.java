package org.darkend.url_shortener.service;

import org.darkend.url_shortener.entity.ShortUrl;
import org.darkend.url_shortener.entity.Url;
import org.darkend.url_shortener.repository.ShortUrlRepository;
import org.darkend.url_shortener.utility.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShortUrlServiceTest {

    private ShortUrl shortUrl;

    private ShortUrlService service;
    private ShortUrlRepository repository;
    private Validator validator;

    @BeforeEach
    void setUp() {
        shortUrl = new ShortUrl("123abc", "http://localhost.com/s/123abc", "http://localhost.com");

        repository = mock(ShortUrlRepository.class);
        validator = mock(Validator.class);
        IdGenerator idGenerator = mock(IdGenerator.class);
        service = new ShortUrlService(repository, validator, idGenerator);

        when(validator.validate(any())).thenReturn(Set.of());
        when(repository.findAll()).thenReturn(List.of(shortUrl));
        when(repository.findById("123abc")).thenReturn(Optional.of(shortUrl));
        when(repository.findById("1234bc")).thenReturn(Optional.empty());
        when(idGenerator.generateId()).thenReturn("123abc");
        when(repository.save(any(ShortUrl.class))).thenAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            return args[0];
        });
    }

    @Test
    void createShortUrlShouldCreateAndSaveShortUrl() {
        when(repository.findById("123abc")).thenReturn(Optional.empty());
        var result = service.createShortUrl(new Url("http://localhost.com"), "localhost.com");

        assertThat(result).isEqualTo(shortUrl);
        verify(repository, times(1)).save(result);
    }

    @Test
    void createShortUrlWithInvalidUrlShouldThrowException() {
        when(repository.findById("123abc")).thenReturn(Optional.empty());
        when(validator.validate(any())).thenThrow(ConstraintViolationException.class);
        assertThatThrownBy(() -> service.createShortUrl(new Url("http://localhost"), "localhost.com"))
                .isExactlyInstanceOf(ConstraintViolationException.class);
        verify(repository, times(1)).findById("123abc");
    }

    @Test
    void getShortUrlShouldReturnCorrectShortUrl() {
        var result = service.getShortUrl("123abc");

        assertThat(result).isEqualTo(shortUrl);
        verify(repository, times(1)).findById("123abc");
    }

    @Test
    void getShortUrlWithInvalidIdShouldThrowException() {
        assertThatThrownBy(() -> service.getShortUrl("1234bc"));

        verify(repository, times(1)).findById("1234bc");
    }

    @Test
    void getAllShouldReturnAllShortUrls() {
        var result = service.getAllShortUrls();

        assertThat(result).containsExactly(shortUrl);
        verify(repository).findAll();
    }

}
