package com.CabBook.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.DTO.RideDTO;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.request.RideRequest;
import com.CabBook.cab.service.interfacefile.DriverService;
import com.CabBook.cab.service.interfacefile.RideService;
import com.CabBook.cab.service.interfacefile.UserService;

@RestController
@RequestMapping("/api/rides")
public class Ridecontroller {

  @Autowired
  private DriverService driverService;

  @Autowired
  private RideService rideService;

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<RideDTO> userRequestRideHandler(@RequestBody RideRequest rideRequest,
      @RequestHeader("Authorization") String jwt) {
    User user = userService.findUserByToken(jwt);
    Ride ride = rideService.requestRide(rideRequest, user);
    RideDTO rideDto = DtoMapper.toRideDto(ride);
    return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
  }

}
