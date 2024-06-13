package com.CabBook.cab.DTO;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.models.Vehicle;

public class DriverDTO {

  private Integer id;
  private String name;
  private String email;
  private double mobile;
  private double latitude;
  private double longitude;
  private UserRole role;
  private Vehicle vehicle;
}
