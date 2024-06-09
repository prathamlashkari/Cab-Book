package com.CabBook.cab.response;

import com.CabBook.cab.enums.UserRole;

import lombok.Data;

@Data
public class JwtResponse {

  private String jwt;
  private String message;
  private boolean isAuthenticated;
  private boolean isError;
  private String errorDetails;
  private UserRole role;
}
