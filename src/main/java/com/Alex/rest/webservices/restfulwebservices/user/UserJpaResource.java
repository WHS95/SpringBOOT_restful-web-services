package com.Alex.rest.webservices.restfulwebservices.user;


import com.Alex.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.Alex.rest.webservices.restfulwebservices.jpa.PostRespository;
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

    private final UserRepository userRepository;
    private final PostRespository postRepository;


    public UserJpaResource(UserDaoService service, UserRepository userRepository, PostRespository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/user")
    public List<User> getUsersInfo() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/user/{id}")
    public EntityModel<User> getUserInfo(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsersInfo());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    //사용자 생성 body에 JSON 형태의 값을 담아보내서
    @PostMapping(path = "/jpa/user")
    public ResponseEntity<Object> saveUserInfo(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        //응답값에 요청 결과 location url 보내는 법
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //API 응답값 반영
        return ResponseEntity.created(location).build();
    }


    //사용자의 id를 기준으로 사용자 삭제
    @DeleteMapping(path = "/jpa/user/{id}")
    public void deleteUserInfo(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }


    //사용자의 id를 기준으로 posts를 가져오기
    @GetMapping(path = "/jpa/user/{id}/posts")
    public List<Post> getUserOfPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        return user.get().getPost();
    }

    //사용자 id를 기준으로 post 등록하기
    @PostMapping(path = "/jpa/user/{id}/posts")
    public ResponseEntity<Object> saveUserPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        //응답값에 요청 결과 location url 보내는 법
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        //API 응답값 반영
        return ResponseEntity.created(location).build();
    }
}
