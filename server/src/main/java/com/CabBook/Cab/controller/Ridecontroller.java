package com.CabBook.cab.controller;

import javax.annotation.processing.Messager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.DTO.DtoMapper;
import com.CabBook.cab.DTO.RideDTO;
import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.exceptions.RideException;
import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.request.RideRequest;
import com.CabBook.cab.response.MessageResponse;
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

  @PostMapping("/request")
  public ResponseEntity<RideDTO> userRequestRideHandler(@RequestBody RideRequest rideRequest,
      @RequestHeader("Authorization") String jwt) throws DriverException, UserException {
    User user = userService.findUserByToken(jwt);
    Ride ride = rideService.requestRide(rideRequest, user);
    RideDTO rideDto = DtoMapper.toRideDto(ride);
    return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
  }

  @PutMapping
  public ResponseEntity<MessageResponse> acceptRideHandler(@PathVariable String rideId)
      throws UserException, RideException, DriverException {

    rideService.acceptRide(rideId);
    MessageResponse messageResponse = new MessageResponse("Ride Accepted By Driver");
    return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);

  }

}
