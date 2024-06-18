package com.pratham.cabserver.utils;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

import io.jsonwebtoken.Jwts;

public class JwtUtils {

  public String generaJwtToken(Authentication authentication)
  {
    String jwt = Jwts.builder().setIssuer("pratham")
  }

}
