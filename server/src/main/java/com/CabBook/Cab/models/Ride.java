package com.CabBook.Cab.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.CabBook.Cab.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "")
public class Ride {

  @Id
  private String Id;
  private String userId;
  private String driverId;

  private List<Integer> declinedDrivers = new ArrayList<>();
  private double pickupLatitude;
  private double pickupLongitude;
  private double destinationLatitude;
  private double destinationLongitude;
  private String pickupArea;
  private String destinationArea;
  private double distance;
  private long duration;
  private double fare;
  private RideStatus status;
  private LocalDateTime starTime;
  private LocalDateTime endTime;
  private int opt;

  private PaymentDetails paymentDetails = new PaymentDetails<>();
}
