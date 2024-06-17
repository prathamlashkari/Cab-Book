package com.pratham.cabserver.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {

    https.csrf(config -> config.disable());
    https.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    https.authorizeHttpRequests(
        config -> config.requestMatchers("/api/auth").permitAll()
            .anyRequest().authenticated());
    https.cors(
        config -> config.configurationSource(new CorsConfigurationSource() {

          @SuppressWarnings("null")
          @Override
          @Nullable
          public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
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

    https.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return https.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
