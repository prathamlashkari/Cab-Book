package com.pratham.cabserver.response;

import com.pratham.cabserver.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {

  private String jwt;
  private String message;
  private boolean isAuthenticated;
  private boolean isError;
  private String errorDetails;
  private UserRole role;
}
