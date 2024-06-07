package com.CabBook.Cab;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtils {
  private JwtUtils() {
  }

  private static final String SECRET_KEY_STRING = "ksjfniqhwiotkcjxnvklndfoisiowqerijasdnfjsdhfjgknvmeior";
  private static final String ISSUER = "code-with-Golu";

  // Create a SecretKey object from the secret key string
  private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

  public static boolean validateToken(String jwtToken) {
    return parseToken(jwtToken).isPresent();
  }

  public static Optional<String> getEmailFromToken(String jwtToken) {
    var claimsOptional = parseToken(jwtToken);
    return claimsOptional.map(claims -> claims.get("email", String.class));
  }

  private static Optional<Claims> parseToken(String jwtToken) {
    var jwtParser = Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build();
    try {
      Claims claims = jwtParser.parseClaimsJws(jwtToken).getBody();
      return Optional.of(claims);
    } catch (JwtException | IllegalArgumentException e) {
      log.error("Jwt Exception occurred: {}", e.getMessage());
    }
    return Optional.empty();
  }

  public static String generateToken(String email) {
    var date = new Date();
    var expiration = DateUtils.addMinutes(date, 10);
    return Jwts.builder()
        .setId(UUID.randomUUID().toString())
        .setIssuer(ISSUER)
        .setSubject(email)
        .setIssuedAt(date)
        .setExpiration(expiration)
        .signWith(SECRET_KEY)
        .compact();
  }
}
