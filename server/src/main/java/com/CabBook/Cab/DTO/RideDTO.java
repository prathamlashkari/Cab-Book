package com.CabBook.cab.DTO;

import java.time.LocalDateTime;

import com.CabBook.cab.enums.RideStatus;
import com.CabBook.cab.models.PaymentDetails;

@SuppressWarnings("unused")
public class RideDTO {

  private Integer id;
  private UserDTO user;
  private DriverDTO driver;
  private double pickupLatitude;
  private double pickupLongitude;
  private double destinationLatitude;
  private double destinationLongitude;
  private String pickupArea;
  private String destinationArea;
  private double distance;
  private long duration;
  private RideStatus status;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private double fare;
  private PaymentDetails paymentDetails;
  private int opt;
}
