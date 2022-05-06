package org.darkend.url_shortener.bean;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.darkend.url_shortener.utility.JsonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConverterBean {

    @Bean
    public JsonConverter jsonConverter(ObjectWriter ow) {
        return new JsonConverter(ow);
    }
}
