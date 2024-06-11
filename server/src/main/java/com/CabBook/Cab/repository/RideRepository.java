package com.CabBook.cab.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.enums.RideStatus;
import com.CabBook.cab.models.Ride;

@Repository
public interface RideRepository extends MongoRepository<Ride, String> {
  List<Ride> findByDriverIdAndStatus(String driverId, RideStatus status);

}
