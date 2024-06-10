package com.CabBook.cab.utils;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.response.JwtResponse;

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

  private static String convertAuthoritiesToString(Collection<? extends GrantedAuthority> collection) {
    Set<String> authoritySet = new HashSet<>();
    for (GrantedAuthority authority : collection) {
      authoritySet.add(authority.getAuthority());
    }
    return String.join(",", authoritySet);
  }

  public static String generateToken(String email) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new IllegalStateException("No authentication information found in security context.");
    }

    String authorities = convertAuthoritiesToString(authentication.getAuthorities());
    var date = new Date();

    return Jwts.builder()
        .setId(UUID.randomUUID().toString())
        .setIssuer(ISSUER)
        .setSubject(email)
        .setIssuedAt(date)
        .claim("authorities", authorities)
        .setExpiration(new Date(date.getTime() + 864000000))
        .signWith(SECRET_KEY)
        .compact();
  }

  public static ResponseEntity<JwtResponse> response(Authentication authentication, String email, String msg) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = generateToken(email);
    JwtResponse res = new JwtResponse();
    res.setJwt(jwt);
    res.setAuthenticated(true);
    res.setError(false);
    res.setErrorDetails(null);
    res.setRole(UserRole.USER);
    res.setMessage(msg);
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }
}
