package com.CabBook.cab.service.classfile;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CabBook.cab.enums.RideStatus;
import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.exceptions.RideException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.repository.RideRepository;
import com.CabBook.cab.request.RideRequest;
import com.CabBook.cab.service.interfacefile.DriverService;
import com.CabBook.cab.service.interfacefile.RideService;
import com.CabBook.cab.utils.Calculator;

@Service
public class RideServiceImpl implements RideService {

  @Autowired
  private DriverService driverService;
  @Autowired
  private RideRepository rideRepository;
  @Autowired
  private Calculator calculator;
  @Autowired
  private DriverRepository driverRepository;

  @Override
  public Ride requestRide(RideRequest rideRequest, User user) throws DriverException {
    double pickupLatitude = rideRequest.getPickupLatitude();
    double pickupLongitude = rideRequest.getPickupLongitude();
    double destinationLatitude = rideRequest.getDestinationLatitude();
    double destinationLongitude = rideRequest.getPickupLongitude();
    String pickUpArea = rideRequest.getPickupArea();
    String destinationArea = rideRequest.getDestinationArea();

    Ride existingRide = new Ride();
    List<Driver> availableDrivers = driverService.getAvailableDrivers(pickupLatitude, pickupLongitude, existingRide);
    Driver nearestDriver = driverService.findNearesetDriver(availableDrivers, pickupLatitude, pickupLongitude);

    if (nearestDriver == null) {
      throw new DriverException("Driver not found");
    }

    Ride ride = createRideRequest(user, nearestDriver, pickupLatitude, pickupLongitude, destinationLatitude,
        destinationLongitude, pickUpArea, destinationArea);
    return ride;
  }

  @Override
  public Ride createRideRequest(User user, Driver nearsDriver, double pickupLatitiude, double pickupLongitude,
      double destinationLatitude, double destinationLongitude, String pickupArea, String destinationArea) {

    Ride ride = new Ride();
    ride.setDriverId(nearsDriver.getId());
    ride.setUserId(user.getId());
    ride.setPickupLatitude(pickupLatitiude);
    ride.setPickupLongitude(pickupLongitude);
    ride.setDestinationArea(destinationArea);
    ride.setDestinationLongitude(destinationLongitude);
    ride.setStatus(RideStatus.REQUESTED);
    ride.setPickupArea(pickupArea);
    ride.setDestinationArea(destinationArea);

    return rideRepository.save(ride);
  }

  @Override
  public void acceptRide(String rideId) throws RideException, DriverException {
    Optional<Ride> ride = rideRepository.findById(rideId);
    if (ride.isEmpty())
      throw new RideException("Ride not found");

    ride.get().setStatus(RideStatus.ACCEPTED);
    String driverId = ride.get().getDriverId();

    Driver driver = driverService.findDriverById(driverId);

    if (driver == null)
      throw new DriverException("Drive not found for this ride");

    driver.setCurrRideId(ride.get().getId());

    Random random = new Random();
    int otp = random.nextInt(9000) + 1000;
    ride.get().setOpt(otp);

    driverRepository.save(driver);
    rideRepository.save(ride.get());
  }

  @Override
  public void desliceRide(String rideId, String driveId) throws RideException {

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
