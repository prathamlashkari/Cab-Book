package com.CabBook.Cab.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.CabBook.Cab.enums.UserRole;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

  @Id
  private String Id;

  @Indexed(unique = true)
  private String fullName;
  @Indexed(unique = true)
  private String email;
  @Indexed(unique = true)
  private String mobile;
  private String password;
  private String profilePicture;
  private UserRole userRole;

}
