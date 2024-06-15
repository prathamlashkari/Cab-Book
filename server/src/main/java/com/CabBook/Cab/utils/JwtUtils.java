package com.CabBook.cab.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;

import com.CabBook.cab.config.JwtConstatnts;

import io.jsonwebtoken.Jwts;

public class JwtUtils {

  public String generateJwtToken(Authentication authentication) {
    String jwt = Jwts.builder().setIssuer("Code With Zosh")
        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 846000000))
        .claim("email", authentication.getName()).signWith(JwtConstatnts.key).compact();
    return jwt;
  }

}
