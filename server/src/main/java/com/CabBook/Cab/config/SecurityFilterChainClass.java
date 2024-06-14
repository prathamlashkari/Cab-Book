package com.CabBook.cab.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityFilterChainClass {

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https.sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    https.csrf(csrfConfig -> csrfConfig.disable());

    https.authorizeHttpRequests(authConfig -> authConfig.requestMatchers("/api/auth/**").permitAll()
        .requestMatchers("/").permitAll()
        .requestMatchers("/swagger-ul/**", "/").permitAll()
        .requestMatchers("/ws/**").permitAll()
        .anyRequest().authenticated());

    https.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
      @Override
      public CorsConfiguration getCorsConfiguration(@SuppressWarnings("null") HttpServletRequest req) {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedHeaders(Collections.singletonList("*"));
        cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:5173"));
        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        cfg.addExposedHeader("Authorization");
        cfg.setMaxAge(3600L);
        cfg.setAllowCredentials(true);
        return cfg;
      }
    }));

    https.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));

    https.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return https.build();
  }
}
