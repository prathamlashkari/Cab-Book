package com.pratham.cabserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.service.UserService;

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