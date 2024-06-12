package com.CabBook.cab.service.interfacefile;

import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.exceptions.RideException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.request.RideRequest;

public interface RideService {

  public Ride requestRide(RideRequest rideRequest, User user) throws DriverException;

  public Ride createRideRequest(User user, Driver nearsDriver, double pickupLatitiude, double pickupLongitude,
      double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea);

  public void acceptRide(String rideId) throws RideException, DriverException;

  public void declineRide(String rideId, String driveId) throws RideException;

  public void startRide(String rideId, int opt) throws RideException;

  public void completeRide(String rideId) throws RideException, DriverException;

  public void cancelRide(String rideId) throws RideException;

  public Ride findRideById(String rideId) throws RideException;
}
