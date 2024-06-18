package com.pratham.cabserver.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;

import com.pratham.cabserver.config.JwtConstant;

import io.jsonwebtoken.Claims;
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

  public String getEmailFromJwt(String jwt) {
    jwt = jwt.substring(7);
    Claims claims = Jwts.parserBuilder().setSigningKey(JwtConstant.key).build().parseClaimsJws(jwt).getBody();

  }

}
