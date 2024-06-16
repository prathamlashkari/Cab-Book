package com.pratham.cabserver.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RideRequest {

  private double pickupLongitude;
  private double pickupLatitude;
  private double destinationLongitude;
  private double destinationLatitude;
  private String pickupArea;
  private String destinationArea;
}
