package com.Alex.rest.webservices.restfulwebservices.mongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class service {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveZoneDateTime() {
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zone);
        User user = new User();
        user.setName("Instant");
        user.setInstant(Instant.now());



        System.out.println("Instant = " +Instant.now());

        // MongoDB에 저장 (UTC로 변환하여 저장)
        mongoTemplate.insert(user, "setAsia/seoul");

        User user2 = new User();
        user2.setName("LocalDateTime");
        user2.setLocalDateTimec(LocalDateTime.now());

        System.out.println("LocalDateTime = " +LocalDateTime.now());
        mongoTemplate.insert(user2, "setAsia/seoul");

        User user3 = new User();
        user3.setName("LocalDateTime +9 hour");
        user3.setLocalDateTimec(LocalDateTime.now().plusHours(9));

        System.out.println("LocalDateTime +9 hour = " +LocalDateTime.now().plusHours(9));
        mongoTemplate.insert(user3, "setAsia/seoul");
    }



    public void findZoneDateTime() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Instant")); // 필터링 조건
        List<User> result = mongoTemplate.find(query, User.class, "setUTC");
        System.out.println("Instant = " + result.get(0).getInstant());


        Query query2 = new Query();
        query2.addCriteria(Criteria.where("name").is("LocalDateTime")); // 필터링 조건
        List<User> result2 = mongoTemplate.find(query2, User.class, "setUTC");
        System.out.println("LocalDateTime = " + result2.get(0).getLocalDateTime());

//        LocalDateTime date = result.get(0).getDate();
//
//        // 결과 표시 (ZoneDateTime으로 변환)
//        ZoneId Seoul = ZoneId.of("Asia/Seoul");
//        ZoneId UTC = ZoneId.of("UTC");
//        ZonedDateTime localizedTime = date.atZone(Seoul).withZoneSameInstant(UTC);
//        return localizedTime;
    }




        public static void main(String[] args) {
            // 현재 날짜와 시간을 UTC로 생성
            ZonedDateTime nowInUTC = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));
            System.out.println("현재 날짜 및 시간(UTC): " + nowInUTC);

            // UTC에서 서울 시간대(KST)로 변환
            ZonedDateTime nowInKST = nowInUTC.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
            System.out.println("현재 날짜 및 시간(KST): " + nowInKST);

            ZonedDateTime nowInKST2 = nowInKST.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
            System.out.println("현재 날짜 및 시간(KST): " + nowInKST2);

            // 2일 더하기
            ZonedDateTime futureUTC = nowInUTC.plusDays(2);
            System.out.println("2일 후(UTC): " + futureUTC);

            // 3시간 빼기
            ZonedDateTime pastUTC = nowInUTC.minusHours(3);
            System.out.println("3시간 전(UTC): " + pastUTC);
        }
}
