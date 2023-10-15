package com.Alex.rest.webservices.restfulwebservices.jpa;

import com.Alex.rest.webservices.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRespository  extends JpaRepository<Post,Integer> {
}
