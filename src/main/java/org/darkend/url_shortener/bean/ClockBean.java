package org.darkend.url_shortener.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockBean {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
