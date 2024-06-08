package com.CabBook.Cab.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.CabBook.Cab.enums.UserRole;

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

}
