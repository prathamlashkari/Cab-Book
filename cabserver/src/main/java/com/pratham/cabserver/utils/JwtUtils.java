package com.pratham.cabserver.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;

import com.pratham.cabserver.config.JwtConstant;

import io.jsonwebtoken.Jwts;

public class JwtUtils {

  public String generaJwtToken(Authentication authentication) {
    String jwt = Jwts.builder().setIssuer("pratham")
        .setExpiration(new Date(new Date().getTime() + 864000000))
        .claim("email", authentication.getName())
        .signWith(JwtConstant.key)
        .compact();
    return jwt;
  }

}
