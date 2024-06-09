package com.CabBook.cab.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "license")
public class License {

  @Id
  private String licneseId;
  private String licenseState;
  private String licenseExpirationDate;
  private String driverId;
}
