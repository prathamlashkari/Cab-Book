package com.pratham.cabserver.service;

import java.util.List;

import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.request.DriversSignupRequest;

public interface DriverService {

  public Driver registerDriver(DriversSignupRequest req);

  public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, Ride ride);

  public Driver findNearesetDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude);

  public Driver getReqDriverProfile(String jwt) throws DriverException;

  public Ride getDrirversCurrentRide(String driverId) throws DriverException;

  public List<Ride> getAllocatedRides(String driverId) throws DriverException;

  public Driver findDriverById(String driverId) throws DriverException;

  public List<Ride> getCompletedRides(String driverId) throws DriverException;
}
