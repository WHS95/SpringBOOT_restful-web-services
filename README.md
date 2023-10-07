# SpringBOOT_restful-web-services

## 📘 Sprign boot API 어떻게 url 요청을 받으면 bean값이 json 형태로 가는것일까?


### 1: How are our requests handled?
요청은 어떻게 처리되나요?

        DispatcherServlet - Front Controller Pattern
        Mapping servlets: dispatcherServlet urls=[/]
        Auto Configuration (DispatcherServletAutoConfiguration)

-DispatcherServlet은 요청을 받는 역할을 하는데, 이는 프론트 컨트롤러 패턴을 따릅니다. 
서블릿 매핑은 어떤 URL이 DispatcherServlet에 의해 처리되는지 정의합니다.   
여기서는 "/" 경로에 대한 요청을 처리합니다.  
이러한 DispatcherServlet 설정은 DispatcherServletAutoConfiguration 가 실행한다.


### 2: How does HelloWorldBean object get converted to JSON?  

        @ResponseBody + JacksonHttpMessageConverters
        Auto Configuration (JacksonHttpMessageConvertersConfiguration)

-@ResponseBody 어노테이션은 메서드가 HTTP 응답의 본문으로 데이터를 직렬화할 수 있도록 허용합니다.  
JacksonHttpMessageConverters는 객체를 JSON으로 변환하고 반대로 JSON을 객체로 변환하는 데 사용됩니다.  
이러한 JacksonHttpMessageConverters는 설정은 JacksonHttpMessageConvertersConfiguration 가 실행한다

### 3: Who is configuring error mapping?  

        Auto Configuration (ErrorMvcAutoConfiguration)

url 매핑잘못되었을때 에러 화면은 누가 다루나 그것은  
ErrorMvcAutoConfiguration 가 자동으로 다룬다.

### 4: How are all jars available(Spring, Spring MVC, Jackson, Tomcat)?  

        Starter Projects - Spring Boot Starter Web 
        (spring-webmvc, spring-web, spring- boot-starter-tomcat, spring-boot-starter-json)

그렇다면 어떻게 이러한 설정들을 다 자동을 하는가 그것은  
Spring Boot Starter Web 라이브러리가 다가져온다.

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
