package com.pratham.cabserver.config;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

public class JwtConstant {
  public static final String Jwt_Key = "sfpqiewramdncvposehowfnsdjkfnowqepoewmnskasskljdeesf";
  public static final String Jwt_Header = "Authorization";
  public static final SecretKey key = Keys.hmacShaKeyFor(Jwt_Key.getBytes());

}
