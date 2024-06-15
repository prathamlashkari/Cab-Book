package com.CabBook.cab.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String jwt = request.getHeader(JwtConstatnts.JWT_HEADER);
    jwt = jwt.substring(7);

    if (jwt != null) {
      SecretKey key = Keys.hmacShaKeyFor(JwtConstatnts.JWT_KEY.getBytes());
    }
  }

}
