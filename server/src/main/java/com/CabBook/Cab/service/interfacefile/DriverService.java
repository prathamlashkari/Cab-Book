package com.CabBook.cab.service.interfacefile;

import java.util.List;

import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.request.DriversSignupRequest;

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
