package com.CabBook.cab.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriversSignupRequest {

  private String name;
  private String email;
  private String mobile;
  private String latitude;
  private String longitude;
  private String password;

  private String licneseId;
  private String vehicleId;
}
