package com.CabBook.cab.utils;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

  private static final int Earth_Radius = 6371;

  public double calculateDistance(double sourceLat, double sourceLng, double desLat, double desLng) {
    double dLat = Math.toRadians(desLat - sourceLat);
    double dLng = Math.toRadians(desLng - sourceLng);

    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(desLat)) * Math.sin(dLng / 2) *
            Math.sin(dLng / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = Earth_Radius * c;
    return distance;
  }

  public long calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
    Duration duration = Duration.between(startTime, endTime);
    return duration.getSeconds();
  }

  public double calculateFare(double distance) {
    double baseFare = 11;
    double totalFare = baseFare * distance;
    return totalFare;
  }
}
