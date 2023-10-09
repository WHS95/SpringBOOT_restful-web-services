package com.Alex.rest.webservices.restfulwebservices;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }


    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean HelloWorldBean(){
        return new HelloWorldBean("Hello-World");
    }

    // Path Paramerter
    // /user/{id}/todos/{id} => /users/2/todos/200

    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/alex

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean HelloWorlPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }



}
