package com.pratham.cabserver.DTO;

import com.pratham.cabserver.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

  private String id;
  private String name;
  private String email;
  private String mobile;
  private double latitude;
  private double longitude;
  private double rating;
  private UserRole role;
  private String vehicle;
}
