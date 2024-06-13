package com.CabBook.cab.DTO;

import org.springframework.stereotype.Service;

import com.CabBook.cab.models.Driver;

@Service
public class DtoMapper {

  public static DriverDTO toDriverDto(Driver driver) {

    DriverDTO driverDTO = new DriverDTO();
    driverDTO.setEmail(driver.getEmail());
    driverDTO.setId(driver.getId());
    driverDTO.setLatitude(driver.getLatitude());
  }

}
