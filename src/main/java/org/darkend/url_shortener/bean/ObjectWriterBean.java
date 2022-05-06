package org.darkend.url_shortener.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectWriterBean {

    @Bean
    public ObjectWriter objectWriter(ObjectMapper om) {
        return om.writer();
    }
}
