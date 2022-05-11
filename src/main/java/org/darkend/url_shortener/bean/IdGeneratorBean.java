package org.darkend.url_shortener.bean;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.darkend.url_shortener.utility.IdGenerator;

@Factory
public class IdGeneratorBean {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}
