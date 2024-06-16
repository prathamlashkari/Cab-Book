package com.pratham.cabserver.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pratham.cabserver.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "drivers")
public class Driver {

  @Id
  private String Id;

  private String fullName;
  private String mobile;
  private String email;
  private double rating;
  private double longitude;
  private double latitude;
  private UserRole role;
  private String password;

  private String liscenseId;

  private List<String> rides;
  private String vehicleId;
  private String currRideId;
  private Integer totalRevenuse = 0;

}
