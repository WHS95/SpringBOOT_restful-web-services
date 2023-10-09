package com.Alex.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();


    static int userIdCount = 0;

    static {
        users.add(new User(++userIdCount, "Alex", LocalDateTime.now().minusYears(10)));
        users.add(new User(++userIdCount, "Sam", LocalDateTime.now().minusYears(3)));
        users.add(new User(++userIdCount, "Kim", LocalDateTime.now().minusYears(9)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public void saveUserInfo(User user) {
        users.add(new User(++userIdCount, user.getName(), user.getBirthDate()));
    }

}
