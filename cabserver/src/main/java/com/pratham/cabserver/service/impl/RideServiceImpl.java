package com.pratham.cabserver.service.impl;

import org.springframework.stereotype.Service;

import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.exceptions.RideException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.request.RideRequest;
import com.pratham.cabserver.service.RideService;

@Service
public class RideServiceImpl implements RideService {

  @Override
  public Ride requestRide(RideRequest rideRequest, User user) throws DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'requestRide'");
  }

  @Override
  public Ride createRideRequest(User user, Driver nearsDriver, double pickupLatitiude, double pickupLongitude,
      double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createRideRequest'");
  }

  @Override
  public void acceptRide(String rideId) throws RideException, DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'acceptRide'");
  }

  @Override
  public void declineRide(String rideId, String driveId) throws RideException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'declineRide'");
  }

  @Override
  public void startRide(String rideId, int opt) throws RideException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startRide'");
  }

  @Override
  public void completeRide(String rideId) throws RideException, DriverException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'completeRide'");
  }

  @Override
  public void cancelRide(String rideId) throws RideException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
  }

  @Override
  public Ride findRideById(String rideId) throws RideException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findRideById'");
  }

}
