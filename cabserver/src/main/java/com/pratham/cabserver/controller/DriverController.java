package com.pratham.cabserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

  @Autowired
  private DriverService driverService;

  @GetMapping("/profile")
  public ResponseEntity<Driver> getReqDriverProfile(@RequestHeader("Authorization") String jwt) throws DriverException {
    Driver driver = driverService.getReqDriverProfile(jwt);
    if (driver == null) {
      throw new DriverException("Drive not found");
    }
    return new ResponseEntity<>(driver, HttpStatus.OK);
  }

  @GetMapping("/{driverId}/current_ride")
  public ResponseEntity<Ride> getDriversCurrentRideHandler(@PathVariable String driverId) throws DriverException {
    Ride ride = driverService.getDrirversCurrentRide(driverId);
    return new ResponseEntity<>(ride, HttpStatus.ACCEPTED);
  }

  @GetMapping("/{driverId}/allocated")
  public ResponseEntity<List<Ride>> getAllocatedRideHandler(@PathVariable String driverId) throws DriverException {
    List<Ride> rides = driverService.getAllocatedRides(driverId);
    return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
  }

  @GetMapping("/rides/completed")
  public ResponseEntity<List<Ride>> getCompletedRideHandler(@RequestHeader("Authorization") String jwt)
      throws DriverException {
    List<Ride> rides = driverService.getCompletedRides(jwt);
    return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
  }

}
