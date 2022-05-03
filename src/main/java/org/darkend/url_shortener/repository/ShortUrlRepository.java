package org.darkend.url_shortener.repository;

import org.darkend.url_shortener.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {

}
