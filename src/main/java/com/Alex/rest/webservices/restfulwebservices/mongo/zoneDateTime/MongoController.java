package com.Alex.rest.webservices.restfulwebservices.mongo.zoneDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    private final MongoService myService;

    public MongoController(MongoService myService) {
        this.myService = myService;
    }

    @PostMapping
    public void saveMyDocument(@RequestBody MyDocument document) {
        // ZonedDateTime 값 설정
        document.setCreatedAt(ZonedDateTime.now());

        myService.saveMyDocument(document);
    }

    @GetMapping("/test1")
    public void test1() {
         myService.saveDataWithZonedDateTime();
    }
    @GetMapping("/test2")
    public List<MyDocument> test2() {
        return myService.fetchDataWithZonedDateTime();
    }

    @GetMapping("/{id}")
    public MyDocument findMyDocument(@PathVariable String id) {
        return myService.findMyDocumentById(id);
    }


}
