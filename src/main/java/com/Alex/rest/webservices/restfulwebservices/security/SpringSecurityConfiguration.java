package com.Alex.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws  Exception{

        //모든 request는 인증을 받아야한다.
        http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
        // 만약 인증 받지 않은 경우의 응답페이지 구성
        http.httpBasic(withDefaults());
        // CSRF => POST, PUT
        http.csrf().disable();

        return http.build();
    }
}
