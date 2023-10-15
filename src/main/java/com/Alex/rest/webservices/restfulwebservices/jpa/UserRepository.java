package com.Alex.rest.webservices.restfulwebservices.jpa;

import com.Alex.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


}
