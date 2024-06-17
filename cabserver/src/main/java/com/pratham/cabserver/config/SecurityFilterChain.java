package com.pratham.cabserver.config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityFilterChain {

  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    https.csrf(config -> config.disable());

    return https.build();
  }
}
