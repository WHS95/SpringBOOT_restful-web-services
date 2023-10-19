package com.Alex.rest.webservices.restfulwebservices.mongo.zoneDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void saveMyDocument(MyDocument document) {
        mongoTemplate.save(document);
    }

    public List<MyDocument> findAllMyDocuments() {
        return mongoTemplate.findAll(MyDocument.class);
    }

    public MyDocument findMyDocumentById(String id) {
        return mongoTemplate.findById(id, MyDocument.class);
    }

    public void saveDataWithZonedDateTime() {
        MyDocument myData = new MyDocument();
        myData.setId("1");
        myData.setName("Sample Data");
        myData.setCreatedAt(ZonedDateTime.now());


        //not UTC
        //2023-10-19T22:49:37.049156+09:00[Asia/Seoul]

        //2023-10-19T13:50:39.635302Z[UTC]
        mongoTemplate.save(myData);
    }

    public List<MyDocument> fetchDataWithZonedDateTime() {
        return mongoTemplate.findAll(MyDocument.class);
    }

}
