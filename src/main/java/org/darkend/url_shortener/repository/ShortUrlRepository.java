package org.darkend.url_shortener.repository;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.darkend.url_shortener.entity.ShortUrl;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface ShortUrlRepository extends CrudRepository<ShortUrl, String> {

}
