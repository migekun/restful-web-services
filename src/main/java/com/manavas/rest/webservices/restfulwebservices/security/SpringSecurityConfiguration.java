package com.manavas.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //All request are authenticated
        http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
        //if a request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());
        //disable CSRF -> POST & PUT disabled
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
