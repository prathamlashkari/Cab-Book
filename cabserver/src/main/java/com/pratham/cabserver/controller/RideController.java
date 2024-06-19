package com.pratham.cabserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.DTO.DtoMapper;
import com.pratham.cabserver.DTO.RideDTO;
import com.pratham.cabserver.exceptions.DriverException;
import com.pratham.cabserver.exceptions.RideException;
import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.request.RideRequest;
import com.pratham.cabserver.request.StartRideRequest;
import com.pratham.cabserver.response.MessageResponse;
import com.pratham.cabserver.service.DriverService;
import com.pratham.cabserver.service.RideService;
import com.pratham.cabserver.service.UserService;

@RestController
@RequestMapping("/api/rides")
public class RideController {

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

  @PutMapping("/{rideId}/accept")
  public ResponseEntity<MessageResponse> acceptRideHandler(@PathVariable String rideId)
      throws UserException, RideException, DriverException {

    rideService.acceptRide(rideId);
    MessageResponse messageResponse = new MessageResponse("Ride Accepted By Driver");
    return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{rideId}/decline")
  public ResponseEntity<MessageResponse> declineRideHandler(@RequestHeader("Authorization") String jwt,
      @PathVariable String rideId) throws UserException, RideException, DriverException {

    Driver driver = driverService.getReqDriverProfile(jwt);
    rideService.declineRide(rideId, driver.getId());
    MessageResponse res = new MessageResponse("Ride decline by driver");
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{rideId}/start")
  public ResponseEntity<MessageResponse> rideStartHandler(@PathVariable String rideId,
      @RequestBody StartRideRequest req) throws RideException {
    rideService.startRide(rideId, req.getOtp());
    MessageResponse res = new MessageResponse("Ride is started");
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{rideId}/complete")
  public ResponseEntity<MessageResponse> rideCompleteHandler(@PathVariable String rideId)
      throws UserException, RideException, DriverException {
    rideService.completeRide(rideId);
    MessageResponse msg = new MessageResponse("Ride is Completed Thank You For booking cab");
    return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
  }

  @GetMapping("/{rideId}")
  public ResponseEntity<RideDTO> findRideByHandler(@PathVariable String rideId) throws RideException, UserException {
    Ride ride = rideService.findRideById(rideId);
    RideDTO rideDTO = DtoMapper.toRideDto(ride);
    return new ResponseEntity<>(rideDTO, HttpStatus.ACCEPTED);
  }

}