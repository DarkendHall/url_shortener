package org.darkend.url_shortener.bean;

import org.darkend.url_shortener.utility.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorBean {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}
