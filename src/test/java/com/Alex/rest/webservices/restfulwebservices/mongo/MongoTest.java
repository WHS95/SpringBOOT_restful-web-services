package com.Alex.rest.webservices.restfulwebservices.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MongoTest {

    @Autowired
    private  service mongo;

    @Test
    void saveZoneDateTime() {
        mongo.saveZoneDateTime2();
    }

    @Test
    void findZoneDateTime() {
        mongo.findZoneDateTime();
    }
}