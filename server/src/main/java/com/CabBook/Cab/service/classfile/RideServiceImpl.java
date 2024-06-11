package com.CabBook.cab.service.classfile;

import org.springframework.stereotype.Service;

import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.exceptions.RideException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.request.RideRequest;
import com.CabBook.cab.service.interfacefile.RideService;

@Service
public class RideServiceImpl implements RideService {

  @Override
  public Ride createRide(RideRequest rideRequest, User user) throws DriverException {
    throw new UnsupportedOperationException("Unimplemented method 'createRide'");
  }

  @Override
  public Ride createRideRequest(User user, Driver nearsDriver, double pickupLatitiude, double pickupLongitude,
      double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea) {
    throw new UnsupportedOperationException("Unimplemented method 'createRideRequest'");
  }

  @Override
  public void acceptRide(String rideId) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'acceptRide'");
  }

  @Override
  public void desliceRide(String rideId, String driveId) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'desliceRide'");
  }

  @Override
  public void startRide(String rideId, int opt) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'startRide'");
  }

  @Override
  public void completeRide(String rideId) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'completeRide'");
  }

  @Override
  public void cancelRide(String rideId) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
  }

  @Override
  public Ride findRideById(String rideId) throws RideException {
    throw new UnsupportedOperationException("Unimplemented method 'findRideById'");
  }

}
