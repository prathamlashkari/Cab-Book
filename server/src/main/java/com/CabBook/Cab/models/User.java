package com.CabBook.Cab.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

  @Id
  private String Id;

  private String fullName;
  private String email;
  private String mobile;
  private String password;
  private String profilePicture;
  private String UserRole;

}
