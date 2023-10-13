package com.Alex.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {


    //URL을 이용한 버전 관리
    //http://localhost:8080/v1/person
    //http://localhost:8080/v2/person
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("kimBob");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("kim","Bob"));
    }

    //Parameter를 이용한 버전 관리
    //http://localhost:8080/person?version=1
    //http://localhost:8080/person?version=2
    @GetMapping(path ="/person",params="version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameer(){
        return new PersonV1("kimBob");
    }

    @GetMapping(path ="/person",params="version=1")
    public PersonV2 getSecondVersionOfPersonRequestParameer(){
        return new PersonV2(new Name("kim","Bob"));
    }

    //Header를 이용한 버전 관리
    //http://localhost:8080/person/header(header X_API-VERSION=1)
    //http://localhost:8080/person/header(header X_API-VERSION=2)
    @GetMapping(path ="/person",headers="X_API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("kimBob");
    }

    @GetMapping(path ="/person",headers="X_API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("kim","Bob"));
    }

    //MediaType을 이용한 버전 관리
    //V1: http://localhost:8080/person/accept
    //HEADER - Accept:application/vnd.company.app-v1+json
    //@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    //V2: http://localhost:8080/person/accept
    //HEADER - Accept:application/vnd.company.app-v1+json
    //@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }




}
