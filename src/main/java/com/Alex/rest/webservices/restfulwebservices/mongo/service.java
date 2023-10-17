package com.Alex.rest.webservices.restfulwebservices.mongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class service {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveZoneDateTime() {
        ZoneId zone = ZoneId.of("Asia/Seoul");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zone);
        User user = new User();
        user.setName("see");
        user.setDate(zonedDateTime);

        // MongoDB에 저장 (UTC로 변환하여 저장)
        mongoTemplate.insert(user, "mycollection");
    }


    public void saveZoneDateTime2() {
        ZoneId zone = ZoneId.of("Asia/Seoul");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zone);

        // MongoDB에 저장 (UTC로 변환하여 저장)
        mongoTemplate.insert(zonedDateTime, "mycollection");
    }
    public ZonedDateTime findZoneDateTime() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("see")); // 필터링 조건
        List<User> result = mongoTemplate.find(query, User.class, "mycollection");

        ZonedDateTime date = result.get(0).getDate();

        // 결과 표시 (ZoneDateTime으로 변환)
        ZoneId zone = ZoneId.of("Asia/Seoul");
        ZonedDateTime localizedTime = date.withZoneSameInstant(zone);
        return localizedTime;
    }
}
