package com.CabBook.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.exceptions.DriverException;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.service.interfacefile.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

  @Autowired
  private DriverService driverService;

  @GetMapping("/profile")
  public ResponseEntity<Driver> getReqDriverProfile(@RequestHeader("Authorization") String jwt) throws DriverException {
    Driver driver = driverService.getReqDriverProfile(jwt);
    if (driver == null) {
      throw new DriverException("Drive not found");
    }
    return new ResponseEntity<>(driver, HttpStatus.OK);
  }

}
