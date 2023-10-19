package com.Alex.rest.webservices.restfulwebservices.mongo.zoneDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document(collection = "mycollection")
public class MyDocument {
    @Id
    private String id;
    private String name;
    private ZonedDateTime createdAt;

    // 생성자, getter, setter 등 필요한 메서드

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
