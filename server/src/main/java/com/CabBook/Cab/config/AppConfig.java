package com.CabBook.Cab.config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorizeHttpRequests -> authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ul/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/ws/**").permitAll()
                .anyRequest().authenticated())
        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:5713"));
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowedHeaders(Arrays.asList("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;
          }
        }));
    return https.build();
  }
}
