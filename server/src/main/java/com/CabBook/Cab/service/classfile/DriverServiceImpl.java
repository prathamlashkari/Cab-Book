package com.CabBook.cab.service.classfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
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
    private JwtUtils jwtUtils;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private RideRepository rideRepository;

    @Override
    public Driver registerDriver(DriversSignupRequest req) {
        throw new UnsupportedOperationException("Unimplemented method 'registerDriver'");
    }

    @Override
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableDrivers'");
    }

    @Override
    public Driver findNearesetDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {
        throw new UnsupportedOperationException("Unimplemented method 'findNearesetDriver'");
    }

    @Override
    public Driver getReqDriverProfile(String jwt) throws DriverException {
        throw new UnsupportedOperationException("Unimplemented method 'getReqDriverProfile'");
    }

    @Override
    public Ride getDrirversCurrentRide(String driverId) throws DriverException {
        throw new UnsupportedOperationException("Unimplemented method 'getDrirversCurrentRide'");
    }

    @Override
    public List<Ride> getAllocatedRides(String driverId) throws DriverException {
        throw new UnsupportedOperationException("Unimplemented method 'getAllocatedRides'");
    }

    @Override
    public Driver findDriverById(String driverId) throws DriverException {
        throw new UnsupportedOperationException("Unimplemented method 'findDriverById'");
    }

    @Override
    public List<Ride> completedRides(String driverId) throws DriverException {
        throw new UnsupportedOperationException("Unimplemented method 'completedRides'");
    }

}
