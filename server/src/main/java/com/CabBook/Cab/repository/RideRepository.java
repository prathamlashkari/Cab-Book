package com.CabBook.cab.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBook.cab.enums.RideStatus;
import com.CabBook.cab.models.Ride;

public interface RideRepository extends MongoRepository<Ride, String> {
  List<Ride> findByDriverIdAndStatus(String driverId, RideStatus status);

}
