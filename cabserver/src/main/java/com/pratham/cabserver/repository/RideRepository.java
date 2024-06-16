package com.pratham.cabserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pratham.cabserver.enums.RideStatus;
import com.pratham.cabserver.models.Ride;

@Repository
public interface RideRepository extends MongoRepository<Ride, String> {
  List<Ride> findByDriverIdAndStatus(String driverId, RideStatus status);

}
