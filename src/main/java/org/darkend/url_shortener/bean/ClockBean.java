package org.darkend.url_shortener.bean;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import java.time.Clock;

@Factory()
public class ClockBean {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
