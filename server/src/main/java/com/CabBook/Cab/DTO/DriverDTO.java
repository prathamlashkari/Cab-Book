package com.CabBook.cab.DTO;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.models.Vehicle;

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
  private double mobile;
  private double latitude;
  private double longitude;
  private UserRole role;
  private Vehicle vehicle;
}
