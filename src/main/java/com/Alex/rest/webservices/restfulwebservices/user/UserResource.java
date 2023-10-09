package com.Alex.rest.webservices.restfulwebservices.user;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {
    private  final UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/user")
    public List<User> getUsersInfo(){
         return  service.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public User getUserInfo(@PathVariable Integer id){
        return  service.findOne(id);
    }

    @PostMapping(path = "/user")
    public void saveUserInfo(@RequestBody User user){
        service.saveUserInfo(user);
    }

}
