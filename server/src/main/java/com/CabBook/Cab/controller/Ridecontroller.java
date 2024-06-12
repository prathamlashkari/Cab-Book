package com.CabBook.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.service.interfacefile.DriverService;
import com.CabBook.cab.service.interfacefile.RideService;

@RestController
@RequestMapping("/api/rides")
public class Ridecontroller {

  @SuppressWarnings("unused")
  @Autowired
  private DriverService driverService;

  @Autowired
  private RideService rideService;

}
