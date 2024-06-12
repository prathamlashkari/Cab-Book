package com.CabBook.cab.service.classfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CabBook.cab.enums.RideStatus;
import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.License;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.Vehicle;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.repository.LicenseRepository;
import com.CabBook.cab.repository.RideRepository;
import com.CabBook.cab.repository.VehicleRepository;
import com.CabBook.cab.request.DriversSignupRequest;
import com.CabBook.cab.service.interfacefile.DriverService;
import com.CabBook.cab.utils.Calculator;
import com.CabBook.cab.utils.JwtUtils;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    private Calculator distanceCalculater;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private RideRepository rideRepository;

    @Override
    public Driver registerDriver(DriversSignupRequest req) {

        License savdLicense = licenseRepository.save(req.getLicense());

        Vehicle savedVehicle = vehicleRepository.save(req.getVehicle());

        Driver driver = new Driver();
        driver.setEmail(req.getEmail());
        driver.setFullName(req.getName());
        driver.setMobile(req.getMobile());
        driver.setPassword(passwordEncoder.encode(req.getPassword()));
        driver.setRole(UserRole.DRIVER);
        driver.setLatitude(req.getLatitude());
        driver.setLongitude(req.getLongitude());
        driver.setLiscenseId(savdLicense.getId());
        driver.setVehicleId(savedVehicle.getId());

        Driver savedDriver = driverRepository.save(driver);

        savdLicense.setDriverId(savedDriver.getId());
        savedVehicle.setDriverId(savedDriver.getId());

        licenseRepository.save(savdLicense);
        vehicleRepository.save(savedVehicle);
        return driver;
    }

    @Override
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, Ride ride) {

        List<Driver> allDrivers = driverRepository.findAll();
        List<Driver> availableDrivers = new ArrayList<>();

        for (Driver driver : allDrivers) {
            String currRideId = driver.getCurrRideId();

            if (currRideId != null && !currRideId.isEmpty()) {
                Optional<Ride> currRideOpt = rideRepository.findById(currRideId);
                if (currRideOpt.isPresent()) {
                    Ride currRide = currRideOpt.get();
                    if (currRide.getStatus() != RideStatus.COMPLETED) {
                        continue;
                    }
                }
            }

            if (ride.getDeclinedDrivers().contains(driver.getId())) {
                continue;
            }

            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();

            @SuppressWarnings("unused")
            double distance = distanceCalculater.calculateDistance(driverLatitude, driverLongitude, pickupLatitude,
                    pickupLongitude);

            availableDrivers.add(driver);

        }

        return availableDrivers;
    }

    @Override
    public Driver findNearesetDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {

        double min = Double.MAX_VALUE;
        Driver nearestDriver = null;
        for (Driver driver : availableDrivers) {
            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();
            double distance = distanceCalculater.calculateDistance(pickupLatitude, pickupLongitude, driverLatitude,
                    driverLongitude);
            if (min > distance) {
                min = distance;
                nearestDriver = driver;
            }
        }
        return nearestDriver;
    }

    @Override
    public Driver getReqDriverProfile(String jwt) throws DriverException {

        String email = JwtUtils.generateToken(jwt);
        Driver driver = driverRepository.findByEmail(email);
        if (driver == null) {
            throw new DriverException("Driver not found");
        }
        return driver;
    }

    @Override
    public Ride getDrirversCurrentRide(String driverId) throws DriverException {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);

        if (driverOptional.isEmpty()) {
            throw new DriverException("Driver not found with id: " + driverId);
        }

        Driver driver = driverOptional.get();

        String rideId = driver.getCurrRideId();

        if (rideId == null || rideId.isEmpty()) {
            throw new DriverException("Current ride not found for driver with id: " + driverId);
        }

        Optional<Ride> curRideOptional = rideRepository.findById(rideId);

        if (curRideOptional.isEmpty()) {
            throw new DriverException("Current ride not found with id: " + rideId);
        }
        return curRideOptional.get();
    }

    @Override
    public List<Ride> getAllocatedRides(String driverId) throws DriverException {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (!driver.isPresent()) {
            throw new DriverException("Driver not found");
        }
        List<Ride> allocatedRides = rideRepository.findByDriverIdAndStatus(driverId, RideStatus.REQUESTED);
        return allocatedRides;
    }

    @Override
    public Driver findDriverById(String driverId) throws DriverException {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (!driver.isPresent()) {
            throw new DriverException("Driver not found");
        }
        return driver.get();
    }

    @Override
    public List<Ride> getCompletedRides(String jwt) throws DriverException {
        Driver driver = getReqDriverProfile(jwt);
        if (driver == null) {
            throw new DriverException("Driver not found");
        }
        List<Ride> completedRides = rideRepository.findByDriverIdAndStatus(driver.getId(), RideStatus.COMPLETED);
        return completedRides;
    }

}
