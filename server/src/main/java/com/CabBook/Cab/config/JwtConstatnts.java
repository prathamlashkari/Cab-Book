package com.CabBook.cab.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class JwtConstatnts {
  public static final String JWT_KEY = "sakskfjiowennvmcnoiwpwfjspofmakmvqwevtwetqwetvwqrwvrewvwre";
  public static final String JWT_HEADER = "Authorization";
  public static final SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

}
