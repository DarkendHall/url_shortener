package org.darkend.url_shortener.utility;

import java.util.UUID;

public class IdGenerator {

    public IdGenerator() {
    }

    public String generateId() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 6);
    }

}
