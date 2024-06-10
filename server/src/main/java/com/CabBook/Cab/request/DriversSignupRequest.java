package com.CabBook.cab.request;

import com.CabBook.cab.models.License;
import com.CabBook.cab.models.Vehicle;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriversSignupRequest {

  private String name;
  private String email;
  private String mobile;
  private double latitude;
  private double longitude;
  private String password;

  private License license;
  private Vehicle vehicle;
}
