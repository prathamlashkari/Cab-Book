package com.CabBook.cab.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;

import com.CabBook.cab.config.JwtConstatnts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

  public static String generateJwtToken(Authentication authentication) {
    String jwt = Jwts.builder().setIssuer("Code With Zosh")
        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 846000000))
        .claim("email", authentication.getName()).signWith(JwtConstatnts.key).compact();
    return jwt;
  }

  public static String getEmailFromJwt(String jwt) {
    jwt = jwt.substring(7);
    Claims claims = Jwts.parserBuilder().setSigningKey(JwtConstatnts.key).build().parseClaimsJws(jwt).getBody();
    String email = String.valueOf(claims.get("email"));
    return email;
  }

}
