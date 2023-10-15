package com.Alex.rest.webservices.restfulwebservices.helloWorld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


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


    @GetMapping(path = "/hello-world-interantionalized")
    public String HelloWorldInterantionalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return  messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }

}
