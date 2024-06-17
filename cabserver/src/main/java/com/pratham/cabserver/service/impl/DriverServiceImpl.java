package com.pratham.cabserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.request.DriversSignupRequest;
import com.pratham.cabserver.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

  @Override
  public Driver registerDriver(DriversSignupRequest req) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registerDriver'");
  }

  @Override
  public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, Ride ride) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAvailableDrivers'");
  }

  @Override
  public Driver findNearesetDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findNearesetDriver'");
  }

  @Override
  public Driver getReqDriverProfile(String jwt) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getReqDriverProfile'");
  }

  @Override
  public Ride getDrirversCurrentRide(String driverId) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getDrirversCurrentRide'");
  }

  @Override
  public List<Ride> getAllocatedRides(String driverId) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllocatedRides'");
  }

  @Override
  public Driver findDriverById(String driverId) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findDriverById'");
  }

  @Override
  public List<Ride> getCompletedRides(String driverId) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCompletedRides'");
  }

}
