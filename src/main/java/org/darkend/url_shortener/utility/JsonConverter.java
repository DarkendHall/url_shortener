package org.darkend.url_shortener.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonConverter {

    private final ObjectWriter ow;

    public JsonConverter(ObjectWriter ow) {
        this.ow = ow;
    }

    public String convert(Object o) {
        try {
            return ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting object to JSON");
        }
    }
}
