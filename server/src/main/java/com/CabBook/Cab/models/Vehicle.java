package com.CabBook.cab.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "vehicles")
public class Vehicle {

  @Id
  private String id;
  private String make;
  private String model;
  private String year;
  private String colour;
  private String capacity;
  private String licensePlate;
  private String driverId;

}
