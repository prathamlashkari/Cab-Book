package com.pratham.cabserver.service;

import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.exceptions.RideException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.request.RideRequest;

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
