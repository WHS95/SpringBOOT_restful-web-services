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

## 📘 Sprign boot API 응답값 정리

### 1:  200번대는 성공했을때의 경우들   
    
    200 - 요청에 대한 결과가 아무 이상 없이 성공했을때  
    201 — Created (데이터 생성을 완료 했을 경우)  
    204 — No Content (데이터를 업데이트 할때 업데이트 대상이 없는 경우)  

### 2:  400클라이어언에서 서버로의 요청 리소스들이 서버와 불일치 하는경우  
   
    401 — Unauthorized (when authorization fails)   
    400 — Bad Request (such as validation error)   
    404 — Resource Not Found  

### 3:  서버자체의 문제가 발생한경우(이 경우가 안생기도록 해야함)     
    
    500 — Server Error  

### 4: 반영 예제
 

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

## 📘 Validation 예외처리 예제

### 1: @Validation 의존성 주입

   	<!--Validation	check	-->
   	<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-validation</artifactId>
   	</dependency>

### 2:  @Validation 적용  


    2-1) Controller
       @PostMapping(path = "/user")
       public ResponseEntity<Object> saveUserInfo(@Valid @RequestBody User user){
       }


    2-2) DTO에 적용
        @Size(min=2, message = "name character is 2 over")
        private String name;
    
        @Past(message = "birthDate not over the current")
        private LocalDateTime birthDate;


### 3:  ExceptionHandler 설정


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now()
                        ,"TotalErrorsCount: "+ e.getErrorCount() +"/ FirstErrorMessage: "+ e.getFieldError().getDefaultMessage()
                        ,request.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


## 📘 Spring boot Swagger 적용

### 1:  Swagger를 작성하는 법에는 두가지가 있다.  
        Springfox, Springdoc
        그럼 무엇을 사용하는것이 좋은가..  
        꾸준히 지원을 해주는게 가장 중요한 요소이라고 볼때
        현재 Springfox는 2020년이 마지막 이다.  
        그렇지만 springdoc같은 경우는 지금까지 계속 업데이트가 되고있다.

💡springDoc github
      https://github.com/springdoc/springdoc-openapi   


### 2-1: Springdoc 버전별 depnedency  (spring 2.x.x)

           <dependency>
              <groupId>org.springdoc</groupId>
              <artifactId>springdoc-openapi-webmvc-core</artifactId>
              <version>last-release-version</version>
           </dependency>
### 2-2: Springdoc 버전별 depnedency  (spring 3.x.x)

           <dependency>
              <groupId>org.springdoc</groupId>
              <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
              <version>last-release-version</version>
           </dependency>


### 3: 접속경로

       /swagger-ui/index.html

### 💡참고블로그
        https://colabear754.tistory.com/50