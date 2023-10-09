package com.Alex.rest.webservices.restfulwebservices.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Object> saveUserInfo(@RequestBody User user){

        User savedUser =  service.saveUserInfo(user);

        //응답값에 요청 결과 location url 보내는 법
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //API 응답값 반영
        return ResponseEntity.created(location).build();
    }

}
