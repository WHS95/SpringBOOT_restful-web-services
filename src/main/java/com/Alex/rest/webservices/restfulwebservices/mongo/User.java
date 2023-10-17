package com.Alex.rest.webservices.restfulwebservices.mongo;


import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Document
public class User {

    private String name;
    private Instant instant;
    private LocalDateTime localDateTimec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTimec;
    }

    public void setLocalDateTimec(LocalDateTime localDateTimec) {
        this.localDateTimec = localDateTimec;
    }
}
