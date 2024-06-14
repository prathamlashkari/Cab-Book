package com.CabBook.cab.DTO;

import org.springframework.stereotype.Service;

import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;

@Service
public class DtoMapper {

  public static DriverDTO toDriverDto(Driver driver) {

    DriverDTO driverDTO = new DriverDTO();
    driverDTO.setEmail(driver.getEmail());
    driverDTO.setId(driver.getId());
    driverDTO.setLatitude(driver.getLatitude());
    driverDTO.setLongitude(driver.getLongitude());
    driverDTO.setMobile(driver.getMobile());
    driverDTO.setName(driver.getFullName());
    driverDTO.setRating(driver.getRating());
    driverDTO.setRole(driver.getRole());
    driverDTO.setVehicle(driver.getVehicleId());
    return driverDTO;
  }

  public static UserDTO toUserDto(User user) {
    UserDTO userDto = new UserDTO();

    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    userDto.setMobile(user.getMobile());
    userDto.setName(user.getFullName());
    return userDto;
  }

  public static RideDTO toRideDto(Ride ride) {
    RideDTO rideDTO = new RideDTO();
    rideDTO.setDestinationLatitude(ride.getDestinationLatitude());
    rideDTO.setDestinationLongitude(ride.getPickupLongitude());
    rideDTO.setDriverId(ride.getDriverId());
    rideDTO.setUserId(ride.getUserId());

  }

}
