package com.Alex.rest.webservices.restfulwebservices.user;


import com.Alex.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.Alex.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private  final UserDaoService service;
    private  final UserRepository repository;

    public UserJpaResource(UserDaoService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping(path = "/jpa/user")
    public List<User> getUsersInfo(){
         return  repository.findAll();
    }

    @GetMapping(path = "/jpa/user/{id}")
    public EntityModel<User> getUserInfo(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsersInfo());
        entityModel.add(link.withRel("all-users"));

        return  entityModel;
    }

    @PostMapping(path = "/jpa/user")
    public ResponseEntity<Object> saveUserInfo(@Valid @RequestBody User user){
        User savedUser =   repository.save(user);
        //응답값에 요청 결과 location url 보내는 법
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //API 응답값 반영
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/user/{id}")
    public void deleteUserInfo(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
