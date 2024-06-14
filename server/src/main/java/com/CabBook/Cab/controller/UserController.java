package com.CabBook.cab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.service.interfacefile.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("{userId}")
  public ResponseEntity<User> findUserByIdHandler(@PathVariable String userId) throws UserException {
    User createdUser = userService.findUserById(userId);
    return new ResponseEntity<>(createdUser, HttpStatus.ACCEPTED);
  }

  @GetMapping("/profile")
  public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
    User user = userService.getReqUserProfile(jwt);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @GetMapping("/rides/completed")
  public ResponseEntity<List<Ride>> getCompletedRidesHandler(@RequestHeader("Authorization") String jwt)
      throws UserException {
    User user = userService.getReqUserProfile(jwt);
    List<Ride> rides = userService.completeRide(user.getId());
    return new ResponseEntity<>(rides, HttpStatus.ACCEPTED);
  }

}
